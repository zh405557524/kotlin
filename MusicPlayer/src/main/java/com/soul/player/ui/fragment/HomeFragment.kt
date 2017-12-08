package com.soul.player.ui.fragment

import com.itheima.player.model.bean.HomeItemBean
import com.soul.player.adapter.HomeAdapter
import com.soul.player.base.BaseListAdapter
import com.soul.player.base.BaseListFragment
import com.soul.player.base.BaseListPresenter
import com.soul.player.presenter.impl.HomePresenterImpl
import com.soul.player.widget.HomeItemView

/**
 ** @author soul
 * @项目名:player
 * @包名: com.soul.player.ui.fragment
 * @作者：祝明
 * @描述：首页界面
 * @创建时间：2017/12/6 16:36
 */
class HomeFragment : BaseListFragment<List<HomeItemBean>, HomeItemBean, HomeItemView>() {


    override fun getList(response: List<HomeItemBean>): List<HomeItemBean>? {
        return response
    }

    override fun getSpecialPresenter(): BaseListPresenter {
        return HomePresenterImpl(this)
    }

    override fun getSpecialAdapter(): BaseListAdapter<HomeItemBean, HomeItemView> {
        return HomeAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.destroyView()
    }

}


