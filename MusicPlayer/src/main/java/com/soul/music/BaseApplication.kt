package com.soul.music

import android.app.Application
import android.os.Handler
import com.soul.music.utils.UIUtils

/**
 * @描述：应用初始化
 * @作者：祝明
 * @项目名:player
 * @创建时间：2018/3/30 11:18
 */
class BaseApplication : Application() {


    /**
     * 静态方法区
     */
    companion object {
        var mHandler: Handler? = Handler()
    }

    override fun onCreate() {
        super.onCreate()
        //初始化工具类
        UIUtils.init(this, mHandler)
    }

}