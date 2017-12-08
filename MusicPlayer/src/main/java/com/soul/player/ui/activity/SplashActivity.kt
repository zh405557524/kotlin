package com.soul.player.ui.activity

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View
import com.soul.player.MainActivity
import com.soul.player.R
import com.soul.player.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/11/28 16:51
 */
class SplashActivity : BaseActivity(), ViewPropertyAnimatorListener {
    override fun onAnimationEnd(view: View?) {
        //进入主界面
        startActivityAndFinish<MainActivity>()
    }

    override fun onAnimationCancel(view: View?) {
    }

    override fun onAnimationStart(view: View?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {
        ViewCompat
                .animate(iv_splash)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .setListener(this)
                .setDuration(2000)

    }

}