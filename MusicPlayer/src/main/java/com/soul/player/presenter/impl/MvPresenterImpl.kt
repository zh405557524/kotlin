package com.soul.player.presenter.impl

import com.itheima.player.model.bean.MvAreaBean
import com.soul.player.net.MvAreaRequest
import com.soul.player.net.ResponseHandler
import com.soul.player.presenter.interf.MvPresenter
import com.soul.player.view.MvView

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2017/12/13 18:59
 */
class MvPresenterImpl(var mView: MvView) : MvPresenter, ResponseHandler<List<MvAreaBean>> {


    override fun loadData() {
        MvAreaRequest(this).execute()
    }

    override fun onError(type: Int, msg: String?) {
        mView.onError(msg)
    }

    override fun onSuccess(type: Int, result: List<MvAreaBean>) {
        mView.onSuccess(result)
    }
}