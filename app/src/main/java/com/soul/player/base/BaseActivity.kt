package com.soul.player.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * @描述：所有activity的基类
 * @作者：祝明
 * @创建时间：2017/11/28 16:37
 */
abstract class BaseActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initListener()
        initData()
    }

    abstract fun getLayoutId(): Int

    open fun initListener() {

    }

    /**
     * 初始化数据
     */
    open fun initData() {

    }


    fun myToast(msg: String) {
        runOnUiThread { toast(msg) }
    }

    inline fun <reified T : BaseActivity> startActivityAndFinish() {
        startActivity<T>()
        finish()
    }
}