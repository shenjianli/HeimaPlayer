package com.shen.player.model

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/9 13:57
 * 描述:该类主要作用
 */
data class YueDanBean(
 val count:Int,
 val page:Int,
 val page_count:Int,
 val status:Int,
 val total_counts:Int,
 val data :List<YueDanData>
)
data class YueDanData(
    val _id:String,
    val author:String,
    val category:String,
    val createdAt:String,
    val desc:String,
    val images:List<String>,
    val likeCount:Int,
    val likeCounts:Int,
    val markdown:String,
    val publishedAt:String,
    val stars:String,
    val status:String,
    val title:String,
    val type:String,
    val updatedAt:String,
    val url:String,
    val views:Int,
    var videoUrl:String
)