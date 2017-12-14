package com.soul.player.ui.activity

import com.soul.player.R
import com.soul.player.base.BaseActivity
import com.soul.player.model.VideoPlayBean
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard
import kotlinx.android.synthetic.main.activity_video.*

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2017/12/14 11:44
 */
class JiecaoVideoPlayerActivity : BaseActivity() {

    override fun getLayoutId(): Int {

        return R.layout.activity_video
    }

    override fun initData() {
        val data = intent.data
        if (data == null) {
            val videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
            videoplayer.setUp(videoPlayBean.url, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoPlayBean.title)
        } else {
            if (data.toString().startsWith("http")) {
                videoplayer.setUp(data.toString(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, data.toString())
            } else {
                videoplayer.setUp(data.path, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, data.toString())
            }

        }


    }

    override fun onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        JCVideoPlayer.releaseAllVideos()
    }

}