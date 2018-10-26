package com.soul.music

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.Gravity
import android.view.View
import com.soul.music.base.BaseActivity
import com.soul.music.ui.ContentFragmentFactory
import com.soul.music.ui.Scan.ScanMediaActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content.*
import kotlinx.android.synthetic.main.main_left.*
import org.jetbrains.anko.startActivity


/**
 * 主界面
 */
class MainActivity : BaseActivity(), View.OnClickListener {


    override fun getLayout(): Int? {

        return R.layout.activity_main
    }

    override fun initView() {
        //内容
        var adapter = MainFragmentAdapter(supportFragmentManager)
        main_viewpager.adapter = adapter
        song_tabLayout.setupWithViewPager(main_viewpager)
    }

    override fun initData() {

    }

    override fun initEvent() {
        iv_menu.setOnClickListener(this)//菜单
        fl_song_scan.setOnClickListener(this)//扫描本地歌曲
        fl_exit.setOnClickListener(this)//退出
    }

    /**
     * 点击事件
     */
    override fun onClick(v: View?) {
        when (v?.id) {
        //菜单键
            R.id.iv_menu -> {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT)
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT)
                }
            }
        //扫描本地歌曲
            R.id.fl_song_scan -> {
                startActivity<ScanMediaActivity>()
            }

        //退出
            R.id.fl_exit -> {
                finish()
            }

        }
    }

    override fun isNeedStatusView(): Boolean {
        return false
    }

    /**
     * 主界面的ViewPager的Adapter
     */
    private inner class MainFragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            return ContentFragmentFactory.getFragment(position)
        }

        override fun getCount(): Int {
            return ContentFragmentFactory.size()
        }

        override fun getPageTitle(position: Int): CharSequence {
            var title = ""
            when (position) {
                0 -> title = "歌曲"
                1 -> title = "歌手"
                2 -> title = "专辑"
            }
            return title
        }
    }

}

















