package com.shen.player.service

import com.shen.player.model.AudioBean

/**
 * 作者:shenjianli
 * 创建时间： 2021/6/20 18:48
 * 描述:该类主要作用
 */
interface IService {

    fun playItem()
    fun updatePlayState()
    fun isPlaying():Boolean
    fun getDuration(): Int
    fun getProgress(): Int
    fun seekTo(progress: Int)
    fun updatePlayMode()
    fun getPlayMode(): Int
    fun playLast()
    fun playNext()
    fun getPlayList(): List<AudioBean>?
    fun playPosition(position: Int)
}