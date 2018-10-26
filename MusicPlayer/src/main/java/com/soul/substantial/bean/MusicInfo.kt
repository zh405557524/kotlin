package com.soul.substantial.bean

/**
 * @描述：音乐信息
 * @作者：祝明
 * @项目名:player
 * @创建时间：2018/4/2 16:21
 */
class MusicInfo : BaseBean() {

    /**
     * id
     */
    var songId: String? = null

    /**
     * 标题
     */
    var title: String? = null

    /**
     * 专辑封面地址
     */
    var album: String? = null

    /**
     * 艺术家
     */
    var artist: String? = null

    /**
     * 艺术家id
     */
    var artistId: Long? = null

    /**
     * 时长
     */
    var duration: Long? = null

    /**
     * 地址
     */
    var path: String? = null

    override fun toString(): String {
        return "MusicInfo(songId=$songId, " +
                "title=$title, " +
                "album=$album, " +
                "artist=$artist, " +
                "artistId=$artistId, " +
                "duration=$duration, " +
                "path=$path)"
    }


}