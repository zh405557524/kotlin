package com.soul.player.net

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2017/12/8 11:19
 */
open class MRequest<T>(val type: Int, val url: String, val handler: ResponseHandler<T>) {

    fun execute() {
        NetManager.manager.sendRequest(this)
    }

    fun parseResult(result: String?): T {
        val gson = Gson()

        val type = (this.javaClass.genericSuperclass
                as ParameterizedType).getActualTypeArguments()[0]
        val list = gson.fromJson<T>(result, type)
//        val list = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)
        return list
    }

}
