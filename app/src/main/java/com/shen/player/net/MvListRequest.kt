package com.shen.player.net

import com.shen.player.constant.Constant
import com.shen.player.model.HomeItemBean
import com.shen.player.model.YueDanBean

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/16 16:34
 * 描述:该类主要作用
 */
class MvListRequest(type:Int,cateType:String,page:Int,handler: ResponseHandler<YueDanBean>)
    :MRequest<YueDanBean>(type,Constant.getCategoryListUrl(cateType,page,10),handler) {
}