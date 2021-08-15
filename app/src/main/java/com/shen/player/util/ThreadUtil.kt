package com.shen.player.util

import android.os.Handler
import android.os.Looper

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 15:59
 * 描述:该类主要作用
 */
object ThreadUtil {
    val handler = Handler(Looper.getMainLooper())
    fun runOnMainThread(runnable:Runnable){
        handler.post(runnable)
    }
}