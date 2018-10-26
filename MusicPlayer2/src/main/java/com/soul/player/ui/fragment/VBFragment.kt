package com.soul.player.ui.fragment

import android.Manifest
import android.content.AsyncQueryHandler
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.AsyncTask
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.view.View
import com.soul.player.R
import com.soul.player.adapter.VbangAdapter
import com.soul.player.base.BaseFragment
import com.soul.player.model.AudioBean
import com.soul.player.ui.activity.AudioPlayerActivity
import com.soul.player.util.CursorUtil
import kotlinx.android.synthetic.main.fragment_vbang.*
import org.jetbrains.anko.noButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.yesButton

/**
 ** @author soul
 * @项目名:player
 * @包名: com.soul.player.ui.fragment
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/12/6 16:36
 */
class VBFragment : BaseFragment() {

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_vbang, null)
    }

    override fun initData() {
        //动态权限申请

        handlePermission()
    }

    var adapter: VbangAdapter? = null
    private fun handlePermission() {
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        //查看是否有权限
        val checkSelfPermission = ActivityCompat.checkSelfPermission(context, permission)
        if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {
            //已经获取
            loadSongs()
        } else {
            //没有获取权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                //需要弹出
                alert("我们只会访问音乐文件,不会访问隐私照片", "温馨提示") {
                    yesButton { myRequestPermission() }
                    noButton {}
                }.show()
            } else {
                //不需要弹出
                myRequestPermission()
            }
        }

    }


    private fun myRequestPermission() {
        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        requestPermissions(permissions, 1)

    }


    /**
     * 接收权限授权结果
     * requestCode 请求码
     * permissions 权限申请数组
     * grantResults 申请之后结果
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadSongs()
        }
    }

    private fun loadSongs() {
        val resolver = context.contentResolver
        val handler = object : AsyncQueryHandler(resolver) {
            override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
                (cookie as VbangAdapter).swapCursor(cursor)
            }
        }

        handler.startQuery(0, adapter, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                arrayOf(
                        MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.ARTIST
                ), null, null, null)

    }

    override fun initListener() {
        adapter = VbangAdapter(context, null)
        listView.adapter = adapter
        listView.setOnItemClickListener { adapterView, view, i, l ->

            //获取数据集合
            val cursor = adapter?.getItem(i) as Cursor
            //通过当前位置cursor 获取整个播放列表
            val list: ArrayList<AudioBean> = AudioBean.getAudioBeans(cursor)
            //转跳奥音乐播放界面
            //跳转到音乐播放界面
            startActivity<AudioPlayerActivity>("list" to list, "position" to i)

        }
    }


    /**
     * 音乐查询异步任务
     */
    class AudioTask : AsyncTask<ContentResolver, Void, Cursor>() {

        /**
         * 后台执行任务  新线程
         */
        override fun doInBackground(vararg p0: ContentResolver?): Cursor? {
            val cursor = p0[0]?.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    arrayOf(MediaStore.Audio.Media.DATA,
                            MediaStore.Audio.Media.SIZE,
                            MediaStore.Audio.Media.DISPLAY_NAME,
                            MediaStore.Audio.Media.ARTIST),
                    null, null, null)
            return cursor
        }

        /**
         * 将后台任务结果回调到主线程中
         */
        override fun onPostExecute(result: Cursor?) {
            super.onPostExecute(result)
            //打印cursor
            CursorUtil.logCursor(result)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter?.changeCursor(null)
    }

}