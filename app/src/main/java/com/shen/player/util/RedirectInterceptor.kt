package com.shen.player.util

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 16:56
 * 描述:该类主要作用
 */
class RedirectInterceptor:Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)
        val code = response.code
        if (code == 307) {
            //获取重定向的地址
            val location = response.headers["Location"]
            Log.e("重定向地址：", "location = $location")
            //重新构建请求
//            val newRequest = request.newBuilder().url(location!!).build()
//            response = chain.proceed(newRequest)
        }
        return response
    }

}