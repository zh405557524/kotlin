package com.soul.player.net

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2017/12/8 10:14
 */
interface ResponseHandler<T> {

    fun onError(type: Int, msg: String?)

    fun onSuccess(type: Int, result: T)
    
}