package com.soul.player.ui.fragment

import android.view.View
import com.itheima.player.model.bean.MvAreaBean
import com.soul.player.R
import com.soul.player.adapter.MvPagerAdapter
import com.soul.player.base.BaseFragment
import com.soul.player.presenter.impl.MvPresenterImpl
import com.soul.player.view.MvView
import kotlinx.android.synthetic.main.fragment_mv.*

/**
 ** @author soul
 * @项目名:player
 * @包名: com.soul.player.ui.fragment
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/12/6 16:36
 */
class MvFragment : BaseFragment(), MvView {

    val presenter by lazy { MvPresenterImpl(this) }

    override fun initView(): View? {

        return View.inflate(context, R.layout.fragment_mv, null)
    }


    override fun initData() {
        presenter.loadData()
    }

    override fun onError(msg: String?) {
        myToast("加载区域数据失败")
    }

    override fun onSuccess(result: List<MvAreaBean>) {
        //在fragment中管理fragment需要用childFragmentManager
        val adapter = MvPagerAdapter(context, result, childFragmentManager)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = result.size
        tabLayout.setupWithViewPager(viewPager)
    }


}