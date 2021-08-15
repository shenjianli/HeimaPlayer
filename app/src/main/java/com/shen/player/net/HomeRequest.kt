package com.shen.player.net

import com.shen.player.constant.Constant
import com.shen.player.model.HomeItemBean

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 20:01
 * 描述:该类主要作用
 */
class HomeRequest(type:Int,page: Int, handler: ResponseHandler<HomeItemBean>) :
    MRequest<HomeItemBean>(type,Constant.getHomeListUrl(page,10), handler) {

}