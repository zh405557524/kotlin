package com.soul.player.util

import android.content.Intent
import android.support.v7.widget.Toolbar
import com.soul.player.R
import com.soul.player.ui.activity.SettingActivity


/**
 * @描述：TODO
 * @作者：祝明
 * @创建时间：2017/11/28 17:58
 */
interface ToolBarManager {

    val toolbar: Toolbar
    /**
     * 初始化主界面的toolbar
     */
    fun initMainToolBar() {
        toolbar.setTitle("影音")
        toolbar.inflateMenu(R.menu.main)

        toolbar.setOnMenuItemClickListener { a ->
            //跳转到设置界面
            toolbar.context.startActivity(Intent(toolbar.context, SettingActivity::class.java))

            true
        }
    }

    /**
     * 处理设置界面的toolbar
     */
    fun initSettingToolbar() {
        toolbar.setTitle("设置界面")

    }


}