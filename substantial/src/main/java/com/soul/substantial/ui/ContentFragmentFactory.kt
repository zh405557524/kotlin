package com.soul.substantial.ui

import android.support.v4.app.Fragment

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2018/1/25 9:48
 */
open class ContentFragmentFactory {

    var data: Map<Int, Fragment> = HashMap()

    fun getFragment(position: Int): Fragment? {
        var fragment = data.get(position)
        if (fragment == null) {

        }
        return fragment
    }

}