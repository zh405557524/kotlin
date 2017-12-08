package com.soul.player.ui.activity

import android.preference.PreferenceManager
import android.support.v7.widget.Toolbar
import com.soul.player.R
import com.soul.player.base.BaseActivity
import com.soul.player.util.ToolBarManager
import org.jetbrains.anko.find

/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/11/28 18:41
 */
class SettingActivity : BaseActivity(), ToolBarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }


    override fun initData() {
        initSettingToolbar()

        //获取推送通知有没有选中
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val b = sp.getBoolean("push", false)
    }


}