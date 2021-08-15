package com.shen.player.util

import com.shen.player.model.LyricBean
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

/**
 * 作者:shenjianli
 * 创建时间： 2021/8/15 10:46
 * 描述:该类主要作用
 */
object LyricUtil {

    fun parseLyric(file: File):List<LyricBean>{
        //创建集合
        val list = ArrayList<LyricBean>()
        if(!file.exists()){
            list.add(LyricBean(0,"歌词加载错误"))
            return list
        }
//        val bfr = BufferedReader(InputStreamReader(FileInputStream(file),"gbk"))
//        var line = bfr.readLine()
//        while(line != null){
//            line = bfr.readLine()
//        }

        val linesList = file.readLines(Charset.forName("gbk"))
        for (line in linesList){
            val lineList:List<LyricBean> = parseLine(line)
            list.addAll(lineList)
        }

        list.sortBy {
            it.startTime
        }

        return list
    }

    private fun parseLine(line: String): List<LyricBean> {
        val list = ArrayList<LyricBean>()
        var arr = line.split("]")
        val content = arr[arr.size-1]
        for (index in 0 until arr.size -1){
            val startTime = parseTime(arr[index])
            list.add(LyricBean(startTime,content))
        }
        return list
    }

    private fun parseTime(timeStr: String): Int {
        val timeS = timeStr.substring(1)
        val list = timeS.split(":")
        var hour = 0
        var min = 0
        var sec = 0f
        //表示有小时
        if(list.size == 3){
            hour = list[0].toInt() * 60*60*1000
            min = list[1].toInt() * 60*1000
            sec = list[2].toFloat() * 1000
        }
        else{
            min = list[0].toInt() * 60*1000
            sec = list[1].toFloat() * 1000
        }
        return hour + min + sec.toInt()
    }

}
