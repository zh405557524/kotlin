package com.soul.player.net

import com.soul.player.util.ThreadUtil
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * @描述：发送网络请求类
 * @作者：祝明
 * @项目名:player
 * @创建时间：2017/12/8 10:05
 */
class NetManager private constructor() {

    val client by lazy { OkHttpClient() }

    companion object {
        val manager by lazy { NetManager() }
    }

    fun <T> sendRequest(req: MRequest<T>) {
        val request = Request
                .Builder()
                .url(req.url)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call?, e: IOException?) {
                ThreadUtil.runOnMainThread(Runnable { req.handler.onError(req.type, e?.message) })
            }

            override fun onResponse(call: okhttp3.Call?, response: Response?) {
                val parseResult = req.parseResult(response?.body()?.string())

                ThreadUtil.runOnMainThread(Runnable { req.handler.onSuccess(req.type, parseResult) })
            }
        })

    }


}
