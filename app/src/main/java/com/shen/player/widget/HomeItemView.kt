package com.shen.player.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.circleCrop
import com.bumptech.glide.request.RequestOptions
import com.shen.player.R
import com.shen.player.model.HomeItemData
import jp.wasabeef.glide.transformations.CropCircleTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_home.view.*


/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 11:03
 * 描述:该类主要作用
 */
class HomeItemView:RelativeLayout {

    constructor(context: Context?):super(context)
    constructor(context: Context?,attrs:AttributeSet):super(context,attrs)
    constructor(context: Context?,attrs: AttributeSet,defStyleAttr:Int):super(context,attrs,defStyleAttr)

    init {
        View.inflate(context,R.layout.item_home,this)
    }

    fun setData(data: HomeItemData) {
        title.text = data.author
        desc.text = data.desc
//        val client = OkHttpClient.Builder()
//            .followRedirects(false)
//            .addInterceptor(RedirectInterceptor())
//            .sslSocketFactory(
//                SSLContextSecurity.createIgnoreVerifySSL("SSL"),
//                object : X509TrustManager {
//                    override fun checkClientTrusted(
//                        chain: Array<out X509Certificate>?,
//                        authType: String?
//                    ) {
//
//                    }
//
//                    override fun checkServerTrusted(
//                        chain: Array<out X509Certificate>?,
//                        authType: String?
//                    ) {
//
//                    }
//
//                    override fun getAcceptedIssuers(): Array<X509Certificate?> {
//                        return arrayOfNulls(0)
//                    }
//
//                })
//            .build()
//        val req = Request.Builder().url(data.url).build()
//        client.newCall(req).enqueue(object :Callback{
//            override fun onFailure(call: Call, e: IOException) {
//                Log.i("shen","失败")
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                val location = response.headers["Location"]
//                Log.i("shen","地址为：${location}")
//            }
//
//        })
        Glide.with(context).
        load(data.url)
            .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(20,1))).into(bg)

        Glide.with(context).
        load(data.url)
            .apply(RequestOptions.circleCropTransform()).into(icon)

    }
}