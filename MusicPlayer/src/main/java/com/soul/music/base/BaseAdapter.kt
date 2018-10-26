package com.soul.music.base

import android.content.Context
import android.content.res.Resources
import com.soul.music.R
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2018/4/11 15:40
 */
abstract class BaseAdapter<T>(context: Context, layoutId: Int, dataS: List<T>?) : CommonAdapter<T>(context, layoutId, dataS) {

    private val mHeaderAndFooterWrapper: HeaderAndFooterWrapper<T>
    private val mLoadMoreWrapper: LoadMoreWrapper<T>

    var res: Resources

    init {
        res = context.resources
        mHeaderAndFooterWrapper = HeaderAndFooterWrapper(this)
        mLoadMoreWrapper = LoadMoreWrapper(mHeaderAndFooterWrapper)
        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading)
    }

    /**
     * 添加数据
     */
    fun addDataS(data: List<T>) {
        if (mDatas == null) {
            mDatas = ArrayList<T>()
        }
        mDatas.addAll(data)
    }

    /**
     * 设置数据
     */
    fun setData(data: List<T>) {
        mDatas = data
    }

    /**
     * 移除数据
     */
    fun remove(data: T) {
        mDatas?.remove(data)
    }

    /**
     * 清楚所有数据
     */
    fun clear() {
        mDatas?.clear()
    }

    /**
     * 需要加载更多,添加的adapter
     */
    fun getLoadMoreAdapter(): LoadMoreWrapper<T> {

        return mLoadMoreWrapper
    }

    /**
     * 设置监听
     */
    fun setOnLoadMoreListener(loadMoreListener: LoadMoreWrapper.OnLoadMoreListener) {
        mLoadMoreWrapper.setOnLoadMoreListener(loadMoreListener)
    }

    /**
     * 更新界面
     */
    fun notifyDataChanged() {
        mLoadMoreWrapper.notifyDataSetChanged()
        notifyDataSetChanged()
    }


}
