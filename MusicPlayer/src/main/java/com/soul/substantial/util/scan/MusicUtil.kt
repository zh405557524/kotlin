package com.soul.substantial.util.scan

import android.content.Context
import android.provider.MediaStore
import com.soul.substantial.bean.MusicInfo
import com.soul.substantial.config.Constant
import com.soul.substantial.util.DBHelp
import com.soul.substantial.utils.ThreadFactory

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2018/4/2 17:49
 */
class MusicUtil {

    private val proj_music = arrayOf(MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.DURATION)
    private lateinit var mListener: OnScanListener

    /**
     * 开始搜索
     */
    fun startScan(context: Context) {
        ThreadFactory.getNormaPool().execute { scanMusicToSQLite(context) }
    }


    fun setListener(listener: OnScanListener) {
        mListener = listener
    }

    /**
     * 搜索媒体资源
     */
    fun scanMusicToSQLite(context: Context) {
        try {
            val cr = context.contentResolver
            val select = StringBuffer(" 1=1 ")
            // 查询语句：检索出.mp3为后缀名，时长大于1分钟，文件大小大于1MB的媒体文件
            if (Environment.isFilterSize(context)) {
                select.append(" and " + MediaStore.Audio.Media.SIZE + " > " + Constant.FILTER_SIZE)
            }
            if (Environment.isFilterDuration(context)) {
                select.append(" and " + MediaStore.Audio.Media.DURATION + " > " + Constant.FILTER_DURATION)
            }

            val cursor = cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, proj_music,
                    select.toString(), null,
                    MediaStore.Audio.Media.TITLE_KEY)
            val musicInfoS = arrayListOf<MusicInfo>()
            while (cursor!!.moveToNext()) {
                val musicInfo = MusicInfo()
                musicInfo.songId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                musicInfo.title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                musicInfo.path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                musicInfo.album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
                musicInfo.artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                musicInfo.artistId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID))
                musicInfo.duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
                musicInfoS.add(musicInfo)
                mListener.onScan(musicInfo)
            }
            DBHelp.getInstance()?.save(musicInfoS)
            mListener.onSuccess()
        } catch (e: Exception) {
            e.printStackTrace()
            mListener.onFail()
        }
    }

}