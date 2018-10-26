package com.soul.substantial.ui

import android.support.v4.app.Fragment
import com.soul.substantial.ui.album.AlbumFragment
import com.soul.substantial.ui.singer.SingerFragment
import com.soul.substantial.ui.song.SongFragment

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player_bar
 * @创建时间：2018/1/25 9:48
 */
open class ContentFragmentFactory {

    companion object {
        var data: Map<Int, Fragment> = HashMap()

        /**
         * 歌曲
         */
        val FRAGMENT_SONG = 0
        /**
         * 歌手
         */
        val FRAGMENT_SINGER = 1
        /**
         * 专辑
         */
        val FRAGMENT_ALBUM = 2

        fun getFragment(position: Int): Fragment? {
            var fragment = data.get(position)
            if (fragment == null) {
                when (position) {
                    FRAGMENT_SONG -> fragment = SongFragment()//歌曲
                    FRAGMENT_SINGER -> fragment = SingerFragment()//歌手
                    FRAGMENT_ALBUM -> fragment = AlbumFragment()//专辑
                }
            }
            return fragment
        }
        fun size(): Int {
            return 3
        }
    }


}