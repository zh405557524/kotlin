package com.soul.player.ui.fragment

import com.itheima.player.model.bean.MvPagerBean
import com.itheima.player.model.bean.VideosBean
import com.soul.player.adapter.MvListAdapter
import com.soul.player.base.BaseListAdapter
import com.soul.player.base.BaseListFragment
import com.soul.player.base.BaseListPresenter
import com.soul.player.model.VideoPlayBean
import com.soul.player.presenter.impl.MvListPresenterImpl
import com.soul.player.view.MvItemView
import com.soul.player.view.MvListView


/**
 * ClassName:MvPagerFragment
 * Description:mv界面每一个页面fragment
 */
class MvPagerFragment : BaseListFragment<MvPagerBean, VideosBean, MvItemView>(), MvListView {


    var code: String? = null

    override fun  init() {
        code = arguments.getString("args")
    }

    override fun getSpecialAdapter(): BaseListAdapter<VideosBean, MvItemView> {
        return MvListAdapter()
    }

    override fun getSpecialPresenter(): BaseListPresenter {
        return MvListPresenterImpl(code!!, this)
    }

    override fun getList(response: MvPagerBean): List<VideosBean>? {
        return response?.videos
    }

    override fun initListener() {
        super.initListener()
        //设置条目点击事件监听函数
        adapter.setMyListener {
            val videoPlayBean = VideoPlayBean(it.id, it.title, it.url)
            //跳转到视频播放界面
//            startActivity<JiecaoVideoPlayerActivity>("item" to videoPlayBean)
        }
    }
}