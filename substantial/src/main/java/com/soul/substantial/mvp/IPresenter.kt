package com.soul.library.mvp

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2018/1/19 14:18
 */
interface IPresenter {
    /**
     * 绑定
     * @param view 对应的view
     */
    fun attachView(view: IView)


    fun detachView()
}