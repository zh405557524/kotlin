package com.soul.player.adapter

import android.content.Context
import android.view.View
import com.itheima.player.model.bean.YueDanBean
import com.soul.player.base.BaseListAdapter
import com.soul.player.widget.YueDanItemView

/**
 * @描述：
 * @作者：祝明
 * @项目名:player
 * @创建时间：2017/12/6 18:01
 */
class YueDanAdapter : BaseListAdapter<YueDanBean.PlayListsBean, YueDanItemView>() {


    /**
     * 设置数据
     */
    override fun refreshItemView(itemView: YueDanItemView, data: YueDanBean.PlayListsBean) {
        itemView.setData(data)
    }


    /**
     * 获取View
     */
    override fun getItemView(context: Context?): View {
        return YueDanItemView(context)
    }

}