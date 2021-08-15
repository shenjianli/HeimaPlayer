package com.shen.player.util

import android.hardware.SensorEventCallback

/**
 * 作者:shenjianli
 * 创建时间： 2021/7/25 16:33
 * 描述:该类主要作用
 */
object StringUtil {

    val HOUR = 60*60*1000
    val MIN = 60*1000
    val SEC = 1000

    fun  parseDuration(progress:Int):String{
        val hour = progress/ HOUR
        val min = progress% HOUR / MIN
        val sec = progress% MIN/ SEC
        if(hour <=0){
            return String.format("%02d:%02d",min,sec)
        }
        else{
            return String.format("%02d:%02d:%02d",hour,min,sec)
        }
    }
}