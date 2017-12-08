package com.soul.player.base

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.soul.player.R
import com.soul.player.view.BaseView
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.support.v4.find

/**
 ** @author soul
 * @项目名:player
 * @包名: com.soul.player.ui.fragment
 * @作者：祝明
 * @描述：首页界面
 * @创建时间：2017/12/6 16:36
 */
abstract class BaseListFragment<T, T1, V : View> : BaseFragment(), BaseView<T> {


    val recyclerView by lazy { find<RecyclerView>(R.id.rv_home) }
    val adapter by lazy { getSpecialAdapter() }


    val mPresenter by lazy { getSpecialPresenter() }


    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_list, null)
    }

    override fun initListener() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        swl.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN)
        swl.setOnRefreshListener { mPresenter.loadDataS() }
        //滑动监听
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView?.layoutManager
                    if (layoutManager is LinearLayoutManager) {
                        val manager: LinearLayoutManager = layoutManager as LinearLayoutManager
                        val lastPosition = manager.findLastVisibleItemPosition()
                        if (lastPosition == adapter.itemCount - 1) {
                            mPresenter.loadMore(adapter.itemCount / 20)
                        }
                    }
                }
            }
        })
    }

    override fun initData() {
        //初始化数据
        mPresenter.loadDataS()
    }

    override fun onError(message: String?) {
        swl.isRefreshing = false
        myToast("加载数据失败")
    }

    override fun loadSuccess(response: T) {
        //隐藏刷新控件
        swl.isRefreshing = false
        //刷新列表
        adapter.uploadData(getList(response))
    }


    override fun loadMore(response: T) {
        adapter.loadMore(getList(response))
    }


    abstract fun getList(response: T): List<T1>?

    abstract fun getSpecialPresenter(): BaseListPresenter

    abstract fun getSpecialAdapter(): BaseListAdapter<T1, V>
}


