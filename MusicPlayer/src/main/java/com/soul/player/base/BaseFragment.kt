package com.soul.player.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast


/**
 * @描述：所有Fragment
 * @作者：祝明
 * @创建时间：2017/11/28 16:43
 */
abstract class BaseFragment : Fragment(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    open protected fun  init() {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return initView()
    }

    abstract fun initView(): View?

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()
    }


    open fun initListener() {

    }

    /**
     * 数据初始
     */
    open fun initData() {

    }

    fun myToast(msg: String) {
        context.runOnUiThread { toast(msg) }
    }

}