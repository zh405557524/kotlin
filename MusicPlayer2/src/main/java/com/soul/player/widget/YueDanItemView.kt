package com.soul.player.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.itheima.player.model.bean.YueDanBean
import com.soul.player.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.item_yue_dan.view.*

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2017/12/8 17:07
 */
class YueDanItemView : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.item_yue_dan, this)
    }

    fun setData(data: YueDanBean.PlayListsBean) {

        title.text = data.title
        author_name.text = data.creator?.nickName
        count.text = data.videoCount.toString()
        //背景
        Picasso.with(context).load(data.playListBigPic).into(bg)
        Picasso.with(context).load(data.creator?.largeAvatar).transform(CropCircleTransformation()).into(author_image)

    }

}