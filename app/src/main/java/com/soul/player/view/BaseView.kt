package com.soul.player.view

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2017/12/7 18:25
 */
interface BaseView<T> {

    /**
     * 获取数据失败
     */
    fun onError(message: String?)

    /**
     * 加载数据成功
     */
    fun loadSuccess(response: T)

    /**
     * 加载更多成功
     */
    fun loadMore(response: T)
}