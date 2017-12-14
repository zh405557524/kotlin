package com.soul.player

import android.support.v7.widget.Toolbar
import com.soul.player.base.BaseActivity
import com.soul.player.util.FragmentUtil
import com.soul.player.util.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity(), ToolBarManager {

    //
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        initMainToolBar()
    }

    override fun initListener() {
        //设置tab 切换
        bottomBar.setOnTabSelectListener {
            val transaction = supportFragmentManager.beginTransaction()
            val data = FragmentUtil.fragmentUtil.data
            for ((key, value) in data) {
                transaction.hide(value)
            }
            val fragment = FragmentUtil.fragmentUtil.getFragment(it)
            if (fragment?.isAdded!!) {
                transaction.show(fragment)
            } else {
                transaction.add(R.id.fl_content, fragment, it.toString())
            }

            transaction.commit()
        }
    }

}
