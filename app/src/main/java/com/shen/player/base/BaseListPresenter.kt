package com.shen.player.base

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/9 17:41
 * 描述:该类主要作用
 */
interface BaseListPresenter {
    companion object{
        const val INIT_TYPE = 1
        const val LOAD_MORE_TYPE = 2
    }

    fun loadMoreData(page :Int)
    fun loadDatas()
    fun destoryView()
}