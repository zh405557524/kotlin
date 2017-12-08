package com.soul.player.util

import com.soul.player.R
import com.soul.player.base.BaseFragment
import com.soul.player.ui.fragment.HomeFragment
import com.soul.player.ui.fragment.MvFragment
import com.soul.player.ui.fragment.VBFragment
import com.soul.player.ui.fragment.YueDanFragment

/**
 ** @author soul
 * @项目名:player
 * @包名: com.soul.player.util
 * @作者：祝明
 * @描述：fragment 切换帮助类
 * @创建时间：2017/12/6 16:40
 */
class FragmentUtil private constructor() {//私有化构造方法

    val homeFragment by lazy { HomeFragment() }
    val mvFragment by lazy { MvFragment() }
    val vBangFragment by lazy { VBFragment() }
    val yueDanFragment by lazy { YueDanFragment() }

    companion object {
        val fragmentUtil by lazy { FragmentUtil() }
    }

    /*
    根据tabId 获取fragment
     */
    fun getFragment(tabId: Int): BaseFragment? {
        when (tabId) {
            R.id.tab_home ->return homeFragment
            R.id.tab_mv ->return mvFragment
            R.id.tab_vb ->return vBangFragment
            R.id.tab_yd ->return yueDanFragment
        }
        return null
    }


}