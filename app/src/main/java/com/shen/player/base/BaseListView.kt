package com.shen.player.base

import com.shen.player.model.HomeItemData

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/9 17:39
 * 描述:该类主要作用
 */
interface BaseListView<T> {

    fun loadMore(data: T?)
    fun onError(message: String?)
    fun onSuccess(data: T?)

}