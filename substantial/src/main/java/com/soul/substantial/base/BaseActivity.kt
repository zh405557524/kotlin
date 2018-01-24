package com.soul.library.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger

/**
 * @描述:activity的基类
 * @作者：祝明
 * @项目名:player
 * @创建时间：2018/1/17 10:20
 */
abstract class BaseActivity : AppCompatActivity(), AnkoLogger {

    protected var mContext: Context = this

    /**
     * 初始化
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout()!!)
        initView()
        initData()
        iniEvent()
    }

    /**
     * 获取根布局
     */
    abstract fun getLayout(): Int?

    abstract fun initView()

    abstract fun initData()

    protected fun iniEvent() {

    }

}