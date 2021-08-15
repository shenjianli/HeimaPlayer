package com.shen.player.util

import android.os.Environment
import java.io.File

/**
 * 作者:shenjianli
 * 创建时间： 2021/8/15 11:26
 * 描述:该类主要作用
 * 歌曲，歌词下载  https://www.90lrc.cn/
 */
object LyricLoader {

    val dir = File(Environment.getExternalStorageDirectory(),"Download/Lyric")

    fun loadLyricFile(display:String):File{
        return File(dir, "$display.lrc")
    }

}