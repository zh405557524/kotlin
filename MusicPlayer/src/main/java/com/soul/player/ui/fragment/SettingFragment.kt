package com.soul.player.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceScreen
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soul.player.R
import com.soul.player.ui.activity.AboutActivity

/**
 ** @author soul
 * @项目名:player
 * @包名: com.soul.player.ui.fragment
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/12/6 16:07
 */
class SettingFragment : PreferenceFragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        addPreferencesFromResource(R.xml.setting)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onPreferenceTreeClick(preferenceScreen: PreferenceScreen?, preference: Preference?): Boolean {
        val key = preference?.key
        if ("clear_cache".equals(key)) {

        }
        if ("push".equals(key)) {
            //关于

        }
        if ("no_wifi".equals(key)) {
            //关于

        }
        if ("about".equals(key)) {
            //关于
            startActivity(Intent(activity, AboutActivity::class.java))
        }

        return super.onPreferenceTreeClick(preferenceScreen, preference)
    }


}