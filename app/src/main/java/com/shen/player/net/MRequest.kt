package com.shen.player.net

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 19:24
 * 描述:该类主要作用
 */
open class MRequest<RESPONSE>(var type:Int,var url: String, val handler: ResponseHandler<RESPONSE>) {

    fun parseResult(result: String?): RESPONSE {
        val gson = Gson()
        val type = (this.javaClass
            .genericSuperclass as ParameterizedType)
            .actualTypeArguments[0]
        val data = gson.fromJson<RESPONSE>(result, type)
        return data
    }

    fun execute(){
        NetManager.manager.sendRequest(this)
    }

}