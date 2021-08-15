package com.shen.player.model

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/9 18:53
 * 描述:该类主要作用
 */
data class CateItemBean(
    val status:Int,
    val data:List<CateItemData>
)

data class CateItemData(
    val _id:String,
    val coverImageUrl:String,
    val desc:String,
    val title:String,
    val type:String

)