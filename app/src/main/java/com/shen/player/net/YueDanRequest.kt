package com.shen.player.net

import com.shen.player.constant.Constant
import com.shen.player.model.HomeItemBean
import com.shen.player.model.YueDanBean

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/9 13:54
 * 描述:该类主要作用
 */
class YueDanRequest(type:Int,content:String,page: Int, handler: ResponseHandler<YueDanBean>)
    :MRequest<YueDanBean>(type,Constant.getSearchUrl(content,page,10),handler){
}