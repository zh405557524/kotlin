package com.soul.music.ui.song

import android.content.Context
import com.soul.music.R
import com.soul.music.base.BaseAdapter
import com.soul.music.bean.MusicInfo
import com.zhy.adapter.recyclerview.base.ViewHolder

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2018/4/11 16:14f
 */
class SongAdapter(context: Context, data: List<MusicInfo>) : BaseAdapter<MusicInfo>(context, R.layout.song_item, data) {


    var selectedPosition: Int = 0


    override fun convert(holder: ViewHolder?, t: MusicInfo?, position: Int) {



    }







}