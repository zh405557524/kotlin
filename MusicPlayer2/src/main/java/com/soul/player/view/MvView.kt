package com.soul.player.view

import com.itheima.player.model.bean.MvAreaBean

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2017/12/13 17:09
 */
interface MvView {

    fun onError(msg: String?)
    fun onSuccess(result: List<MvAreaBean>)

}