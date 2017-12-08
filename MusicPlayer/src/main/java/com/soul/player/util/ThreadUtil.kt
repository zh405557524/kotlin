package com.soul.player.util

import android.os.Handler
import android.os.Looper

/**
 * @描述：切换UI线程
 * @作者：祝明
 * @项目名:player
 * @创建时间：2017/12/7 14:48
 */
object ThreadUtil {
    val handler = Handler(Looper.getMainLooper())
    fun runOnMainThread(runnable: Runnable) {
        handler.post(runnable)
    }
}