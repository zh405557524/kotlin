package com.soul.substantial.ui.player

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.soul.substantial.R

/**
 * @描述：播放器的自定义View
 * @作者：祝明
 * @项目名:player_bar
 * @创建时间：2018/4/3 17:54
 */
class PlayerBarView : FrameLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.player_bar,this)
    }


}