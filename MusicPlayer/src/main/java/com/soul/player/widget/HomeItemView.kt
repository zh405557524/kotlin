package com.soul.player.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.itheima.player.model.bean.HomeItemBean
import com.soul.player.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2017/12/6 17:32
 */
class HomeItemView : RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * 初始化
     */
    init {
        View.inflate(context, R.layout.item_home, this)
    }

    /**
     * 设置数据
     */
    fun setData(data: HomeItemBean) {
        title.setText(data.title)
        desc.setText(data.description)
        Picasso.with(context).load(data.posterPic).into(bg)
    }
}