package com.soul.player.ui.fragment

import android.view.View
import android.widget.TextView
import com.soul.player.base.BaseFragment

/**
 ** @author soul
 * @项目名:player
 * @包名: com.soul.player.ui.fragment
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/12/6 16:36
 */
class VBFragment : BaseFragment() {

    override fun initView(): View? {

        var tv = TextView(activity)
        tv.text = "VBFragment"

        return tv
    }

}