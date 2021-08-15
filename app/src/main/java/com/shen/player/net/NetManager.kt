package com.shen.player.net

import com.shen.player.constant.SSLContextSecurity
import com.shen.player.util.ThreadUtil
import okhttp3.*
import java.io.IOException
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 19:27
 * 描述:该类主要作用
 */
class NetManager private constructor(){
    val client by lazy {
        OkHttpClient.Builder()
            .sslSocketFactory(
                SSLContextSecurity.createIgnoreVerifySSL("SSL"),
                object : X509TrustManager {
                    override fun checkClientTrusted(
                        chain: Array<out X509Certificate>?,
                        authType: String?
                    ) {

                    }

                    override fun checkServerTrusted(
                        chain: Array<out X509Certificate>?,
                        authType: String?
                    ) {

                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate?> {
                        return arrayOfNulls(0)
                    }

                })
            .build()
    }
    companion object{
        val manager by lazy { NetManager() }
    }

    fun <RESPONSE>sendRequest(req:MRequest<RESPONSE>){
        val request = Request.Builder()
            .url(req.url)
            .get()
            .build()
        client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        req.handler.onError(req.type,e?.message)
                    }
                })
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response?.body?.string()
                val parseResult = req.parseResult(result)
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        req.handler.onSuccess(req.type,parseResult)
                    }

                })
            }

        })
    }

}