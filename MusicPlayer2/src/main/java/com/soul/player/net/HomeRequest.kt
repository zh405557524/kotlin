package com.soul.player.net

import com.itheima.player.model.bean.HomeItemBean

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2017/12/8 10:03
 */
class HomeRequest(type: Int, url: String, handler: ResponseHandler<List<HomeItemBean>>) : MRequest<List<HomeItemBean>>(type, url, handler) {

}