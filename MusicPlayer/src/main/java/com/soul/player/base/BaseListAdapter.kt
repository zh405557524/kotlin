package com.soul.player.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.soul.player.widget.LoadMoreItemView

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2017/12/6 18:01
 */
abstract class BaseListAdapter<T, V : View> : RecyclerView.Adapter<BaseListAdapter.BaseHolder>() {

    var MORE_ITEM = 1//加载更多
    var NORMAL_ITEM = 2//普通条目


    private var list = ArrayList<T>()


    fun uploadData(data: List<T>?) {
        data?.let {
            list.clear()
            list.addAll(data)
            notifyDataSetChanged()
        }
    }

    fun loadMore(data: List<T>?) {
        data?.let {
            list.addAll(data)
            notifyDataSetChanged()
        }
    }


    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: BaseHolder?, position: Int) {
        if (getItemViewType(position) == NORMAL_ITEM) {
            val data = list.get(position)
            val itemView = holder?.itemView as V
            refreshItemView(itemView, data)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseHolder {
        if (viewType == NORMAL_ITEM) {
            return BaseHolder(getItemView(parent?.context))
        } else {
            return BaseHolder(LoadMoreItemView(parent?.context))
        }
    }


    override fun getItemViewType(position: Int): Int {
        if (position == itemCount - 1) {
            //加载更多
            return MORE_ITEM
        } else {
            //普通条目
            return NORMAL_ITEM
        }
    }


    class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    //设置条目数据
    abstract fun refreshItemView(itemView: V, data: T)

    //条目View
    abstract fun getItemView(context: Context?): View

}