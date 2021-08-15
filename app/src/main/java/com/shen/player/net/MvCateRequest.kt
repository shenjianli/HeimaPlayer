package com.shen.player.net

import com.shen.player.constant.Constant
import com.shen.player.model.CateItemBean

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/9 18:51
 * 描述:该类主要作用
 */
class MvCateRequest(handler: ResponseHandler<CateItemBean>):MRequest<CateItemBean>(0,Constant.cateUrl,handler) {
}