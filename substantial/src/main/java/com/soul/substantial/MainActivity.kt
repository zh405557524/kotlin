package com.soul.substantial

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.soul.substantial.base.BaseActivity


/**
 * 主界面
 */
class MainActivity : BaseActivity() {

    override fun getLayout(): Int? {

        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun initData() {

    }


    private inner class MainFragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            return null
        }

        override fun getCount(): Int {
            return 0
        }
    }
}

















