package com.soul.substantial.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.soul.substantial.R
import com.soul.substantial.utils.ImmersiveUtil
import org.jetbrains.anko.AnkoLogger

/**
 * @描述:activity的基类
 * @作者：祝明
 * @项目名:player
 * @创建时间：2018/1/17 10:20
 */
abstract class BaseActivity : AppCompatActivity(), AnkoLogger {

    protected var mContext: Context = this

    private lateinit var mStatusView: View

    private lateinit var mRootView: ViewGroup

    /**
     * 初始化
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersiveUtil.immersive(this)
        setContentView(getLayout()!!)
        initView()
        initData()
        initEvent()
    }

    /**
     * 获取根布局
     */
    abstract fun getLayout(): Int?

    abstract fun initView()

    abstract fun initData()

    open fun initEvent() {

    }


    override fun setContentView(@LayoutRes layoutResID: Int) {
        val view = LayoutInflater.from(mContext).inflate(layoutResID, null, false)
        handleContentView(view, null)
    }

    override fun setContentView(view: View) {
        handleContentView(view, null)
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
        handleContentView(view, params)
    }

    fun getStatusBarColor(): Int {
        return ContextCompat.getColor(mContext, R.color.primary)
    }


    /**
     * 处理contentView 不要直接复写setcontentView之后调用super
     * @param view
     * @param params
     */
    private fun handleContentView(view: View, params: ViewGroup.LayoutParams?) {
        if (isNeedStatusView()) {
            if (view is LinearLayout && view.orientation == LinearLayout.VERTICAL) {
                mRootView = view
            } else {
                mRootView = LinearLayout(this)
                (mRootView as LinearLayout).orientation = LinearLayout.VERTICAL

                val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                mRootView.addView(view, layoutParams)
            }

            mStatusView = ImmersiveUtil.createStatusView(this, getStatusBarColor())
            mRootView.addView(mStatusView, 0)
            checkAndSetContentView(mRootView, params)
        } else {
            checkAndSetContentView(view, params)
        }
    }


    private fun checkAndSetContentView(view: View, params: ViewGroup.LayoutParams?) {
        if (params != null) {
            super.setContentView(view, params)
        } else {
            super.setContentView(view)
        }
    }

    /**
     * 是否需要显示标题栏
     */
    open fun isNeedStatusView(): Boolean {
        return true
    }

    fun onBackClicked(view: View) {
        onBackPressed()
    }

    protected fun isActivityNeedBus(): Boolean {
        return false
    }
}