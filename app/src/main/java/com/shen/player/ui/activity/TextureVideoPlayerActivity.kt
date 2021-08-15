package com.shen.player.ui.activity

import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.view.Surface
import android.view.TextureView
import com.shen.player.R
import com.shen.player.base.BaseActivity
import com.shen.player.model.VideoPlayerBean
import kotlinx.android.synthetic.main.activity_video_player.*
import kotlinx.android.synthetic.main.activity_video_player_texture.*

/**
 * 作者:shenjianli
 * 创建时间： 2021/6/6 11:17
 * 描述:该类主要作用
 */
class TextureVideoPlayerActivity:BaseActivity(), TextureView.SurfaceTextureListener {

    val mediaPlayer by lazy { MediaPlayer() }
    override fun getLayoutId(): Int {
        return R.layout.activity_video_player_texture
    }

    override fun initData() {
        super.initData()
        val videoPlayerBean = intent.getParcelableExtra<VideoPlayerBean>("item")
        println("itemBean=$videoPlayerBean")
//        videoView.setVideoPath(videoPlayerBean?.videoUrl)
//        videoView.setOnPreparedListener {
//            videoView.start()
//        }
        textureView.surfaceTextureListener = this
    }

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {

    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
        mediaPlayer?.let {
            mediaPlayer?.stop()
            mediaPlayer?.release()
        }

        return true
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
        println("width= $width height= $height")
        val videoPlayerBean = intent.getParcelableExtra<VideoPlayerBean>("item")
        videoPlayerBean?.let {
            //视图可用
            //val meidaPlay = MediaPlayer()
            mediaPlayer.setDataSource(it.videoUrl)
            mediaPlayer.setSurface(Surface(surface))
            mediaPlayer.setOnPreparedListener {
                mediaPlayer.start()
            }
            mediaPlayer.prepareAsync()
            //textureView.rotation = 100f
        }
    }

}