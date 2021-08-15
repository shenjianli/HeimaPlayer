package com.shen.player.util

import android.database.Cursor
import android.util.Log

/**
 * 作者:shenjianli
 * 创建时间： 2021/6/20 10:23
 * 描述:该类主要作用
 */
object CursorUtil {

    val TAG:String = javaClass.simpleName

    fun logCursor(cursor: Cursor?){
        cursor?.let {
            it.moveToPosition(-1)
            while (it.moveToNext()){
                for (index in 0 until it.columnCount){
                    Log.i(TAG,"key = ${it.getColumnName(index)} " +
                            "--value=${it.getString(index)}")
                }
            }
        }
    }
}