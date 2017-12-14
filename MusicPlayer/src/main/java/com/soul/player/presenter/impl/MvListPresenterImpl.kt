package com.soul.player.presenter.impl

import com.itheima.player.model.bean.MvPagerBean
import com.soul.player.base.BaseListPresenter
import com.soul.player.net.MvListRequest
import com.soul.player.net.ResponseHandler
import com.soul.player.presenter.interf.MvListPresenter
import com.soul.player.view.MvListView


/**
 * ClassName:MvListPresenterImpl
 * Description:
 */
class MvListPresenterImpl(var code: String, var mvListView: MvListView?) : MvListPresenter, ResponseHandler<MvPagerBean> {


    override fun onError(type: Int, msg: String?) {
        mvListView?.onError(msg)
    }

    override fun onSuccess(type: Int, result: MvPagerBean) {
        if (type == BaseListPresenter.TYPE_INIT_OR_REFRESH) {
            mvListView?.loadSuccess(result)
        } else if (type == BaseListPresenter.TYPE_LOAD_MORE) {
            mvListView?.loadMore(result)
        }
    }

    override fun loadMore(offset: Int) {
        MvListRequest(BaseListPresenter.TYPE_LOAD_MORE, code, offset, this).execute()
    }


    override fun loadDataS() {
        MvListRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH, code, 0, this).execute()

    }

    override fun destroyView() {

        if (mvListView != null) {
            mvListView = null
        }
    }
}