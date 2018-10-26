package com.soul.substantial

import android.animation.Animator
import android.animation.ValueAnimator
import android.support.v4.content.ContextCompat
import android.view.WindowManager
import com.soul.substantial.base.BaseActivity
import com.soul.substantial.config.AppConfig.IS_SCAN
import com.soul.substantial.ui.Scan.ScanMediaActivity
import com.soul.substantial.utils.SPUtils
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity
import java.util.*

/**
 * 欢迎界面
 */
class SplashActivity : BaseActivity() {

    /**
     * 启动时间
     */
    var startTime: Long = 1500

    override fun getLayout(): Int? {
        return R.layout.activity_splash
    }

    override fun initView() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        gtp_appname.maxValue = 100
        gtp_appname.setProColorInt(ContextCompat.getColor(mContext, R.color.primary))
        rl_splash.setBackgroundResource(getBackgroundResId())
    }

    override fun initData() {
        //文字动画
        doTextAnimator()
    }

    /**
     * 获取背景图
     */
    fun getBackgroundResId(): Int {
        var resId = R.drawable.img_splash0
        when (Random().nextInt(6)) {
            0 -> resId = R.drawable.img_splash0
            1 -> resId = R.drawable.img_splash1
            2 -> resId = R.drawable.img_splash2
            3 -> resId = R.drawable.img_splash3
            4 -> resId = R.drawable.img_splash4
            5 -> resId = R.drawable.img_splash5
            6 -> resId = R.drawable.img_splash6
        }
        return resId
    }

    /**
     * 文字颜色动画
     */
    fun doTextAnimator() {
        val animator = ValueAnimator.ofInt(0, 100)
        animator.duration = startTime
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                val is_scan = SPUtils.get(IS_SCAN, 0)
                if (is_scan == 0) {
                    startActivity<ScanMediaActivity>()
                } else {
                    startActivity<MainActivity>()
                }
                finish()
            }
        })
        animator.addUpdateListener { animation ->
            gtp_appname.progress = animation.getAnimatedValue() as Int
        }
        animator.start()
    }


    override fun isNeedStatusView(): Boolean {
        return false
    }
}
