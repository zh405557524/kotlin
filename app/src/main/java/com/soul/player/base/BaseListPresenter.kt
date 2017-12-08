package com.soul.player.base

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2017/12/7 18:23
 */
interface BaseListPresenter {

    companion object {
        val TYPE_INIT_OR_REFRESH = 1
        val TYPE_LOAD_MORE = 2
    }

    fun loadDataS()
    fun loadMore(offset: Int)
    fun destroyView()

}