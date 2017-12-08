package com.soul.player.adapter

import android.content.Context
import android.view.View
import com.itheima.player.model.bean.HomeItemBean
import com.soul.player.base.BaseListAdapter
import com.soul.player.widget.HomeItemView

/**
 * @描述：首页Adapter
 * @作者：祝明
 * @项目名:player
 * @创建时间：2017/12/6 18:01
 */
class HomeAdapter : BaseListAdapter<HomeItemBean, HomeItemView>() {


    /**
     * 设置数据
     */
    override fun refreshItemView(itemView: HomeItemView, data: HomeItemBean) {
        itemView.setData(data)
    }

    /**
     * 获取View
     */
    override fun getItemView(context: Context?): View {
        return HomeItemView(context)
    }

}