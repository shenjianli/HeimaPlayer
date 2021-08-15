package com.shen.player.net

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 19:25
 * 描述:该类主要作用
 */
interface ResponseHandler<RESPONSE> {
    fun onError(type:Int,msg:String?)
    fun onSuccess(type:Int,result:RESPONSE)
}