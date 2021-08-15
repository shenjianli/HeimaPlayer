package com.shen.player.ui.activity

import com.shen.player.R
import com.shen.player.base.BaseActivity
import com.shen.player.model.VideoPlayerBean
import kotlinx.android.synthetic.main.activity_video_player.*

/**
 * 作者:shenjianli
 * 创建时间： 2021/6/6 11:17
 * 描述:该类主要作用
 */
class VideoPlayerActivity:BaseActivity() {


    override fun getLayoutId(): Int {
        return R.layout.activity_video_player
    }

    override fun initData() {
        super.initData()
        val videoPlayerBean = intent.getParcelableExtra<VideoPlayerBean>("item")
        println("itemBean=$videoPlayerBean")
        videoView.setVideoPath(videoPlayerBean?.videoUrl)
        videoView.setOnPreparedListener {
            videoView.start()
        }
    }

}