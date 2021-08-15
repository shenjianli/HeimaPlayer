package com.shen.player.view

import com.shen.player.model.CateItemBean

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/9 18:39
 * 描述:该类主要作用
 */
interface MvView {
    fun onError(msg: String?)
    fun onSuccess(result: CateItemBean)
}