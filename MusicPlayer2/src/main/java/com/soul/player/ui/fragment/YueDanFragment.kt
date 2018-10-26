package com.soul.player.ui.fragment

import com.itheima.player.model.bean.YueDanBean
import com.soul.player.adapter.YueDanAdapter
import com.soul.player.base.BaseListAdapter
import com.soul.player.base.BaseListFragment
import com.soul.player.base.BaseListPresenter
import com.soul.player.presenter.impl.YueDanPresenterImpl
import com.soul.player.widget.YueDanItemView

/**
 ** @author soul
 * @项目名:player
 * @包名: com.soul.player.ui.fragment
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/12/6 16:36
 */
class YueDanFragment : BaseListFragment<YueDanBean, YueDanBean.PlayListsBean, YueDanItemView>() {
    override fun getList(response: YueDanBean): List<YueDanBean.PlayListsBean>? {
        return response?.playLists
    }

    override fun getSpecialPresenter(): BaseListPresenter {
        return YueDanPresenterImpl(this)
    }

    override fun getSpecialAdapter(): BaseListAdapter<YueDanBean.PlayListsBean, YueDanItemView> {
        return YueDanAdapter()
    }

}