package com.soul.substantial.base

import android.os.Bundle
import com.soul.library.mvp.IModel
import com.soul.library.mvp.IView

/**
 * @描述：P层Fragment的基类
 * @作者：祝明
 * @项目名:player
 * @创建时间：2018/1/19 17:39
 */
abstract class BaseFragmentPresenter<V : IView, M : IModel, P : BasePresenter<V, M>> : BaseFragment(), IView {

    protected var mPresenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = loadPresenter()
        initCommonData()
    }

    abstract fun loadPresenter(): P?

    private fun initCommonData() {
        mPresenter?.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }
    
}