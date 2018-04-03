package com.soul.substantial.base

import android.os.Bundle
import com.soul.library.mvp.IModel
import com.soul.library.mvp.IView

/**
 * @描述：p层activity的基类
 * @作者：祝明
 * @项目名:player
 * @创建时间：2018/1/17 10:26
 */
abstract class BaseActivityPresenter<V : IView, M : IModel, P : BasePresenter<V, M>> : BaseActivity(), IView {

    protected var mPresenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        mPresenter = loadPresenter()
        initCommonData()
        super.onCreate(savedInstanceState)
    }

    abstract fun loadPresenter(): P

    private fun initCommonData() {
        mPresenter?.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

}