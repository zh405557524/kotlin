package com.soul.player.presenter.impl

import com.itheima.player.model.bean.HomeItemBean
import com.soul.player.base.BaseListPresenter.Companion.TYPE_INIT_OR_REFRESH
import com.soul.player.base.BaseListPresenter.Companion.TYPE_LOAD_MORE
import com.soul.player.net.HomeRequest
import com.soul.player.net.ResponseHandler
import com.soul.player.presenter.interf.HomePresenter
import com.soul.player.util.URLProviderUtils
import com.soul.player.view.BaseView

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2017/12/7 18:24
 */
class HomePresenterImpl(var homeView: BaseView<List<HomeItemBean>>?) : HomePresenter, ResponseHandler<List<HomeItemBean>> {


    override fun loadDataS() {
        val path = URLProviderUtils.getHomeUrl(0, 20)
        HomeRequest(TYPE_INIT_OR_REFRESH, path, this).execute()
    }

    override fun loadMore(offset: Int) {
        val path = URLProviderUtils.getHomeUrl(offset, 20)
        HomeRequest(TYPE_LOAD_MORE, path, this).execute()
    }


    override fun onError(type: Int, msg: String?) {
        homeView?.onError(msg)
    }

    override fun onSuccess(type: Int, result: List<HomeItemBean>) {
        when (type) {
            TYPE_INIT_OR_REFRESH -> homeView?.loadSuccess(result)
            TYPE_LOAD_MORE -> homeView?.loadMore(result)
        }

    }


    override fun destroyView() {
        homeView = null
    }

}