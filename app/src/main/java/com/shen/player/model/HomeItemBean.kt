package com.shen.player.model

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 15:37
 * 描述:该类主要作用
 */
data class HomeItemBean(
    var data: List<HomeItemData>,
    var page: Int,
    var page_count: Int,
    var status: Int,
    var total_counts: Int
)