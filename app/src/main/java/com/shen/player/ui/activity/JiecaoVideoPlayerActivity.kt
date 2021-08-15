package com.shen.player.ui.activity

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore.MediaColumns
import android.util.Log
import com.bumptech.glide.Glide
import com.shen.player.R
import com.shen.player.base.BaseActivity
import com.shen.player.model.VideoPlayerBean
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard
import kotlinx.android.synthetic.main.activity_video_player_jiecao.*


/**
 * 作者:shenjianli
 * 创建时间： 2021/6/6 11:17
 * 描述:该类主要作用
 */
class JiecaoVideoPlayerActivity:BaseActivity() {


    override fun getLayoutId(): Int {
        return R.layout.activity_video_player_jiecao
    }

    override fun initData() {
        super.initData()

        val data = intent.data
        if (data == null){
            val videoPlayerBean = intent.getParcelableExtra<VideoPlayerBean>("item")
            println("itemBean=$videoPlayerBean")
            //videoPlayerBean?.videoUrl = "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"

            videoView.setUp(videoPlayerBean?.videoUrl, JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, videoPlayerBean?.title)
            if(!videoPlayerBean?.imgUrl.isNullOrEmpty()){
                Glide.with(this).load(videoPlayerBean?.imgUrl).into(videoView.thumbImageView)
            }

//        val jcVideoPlayerStandard =
//            findViewById<View>(R.id.custom_videoplayer_standard) as JCVideoPlayerStandard
//        jcVideoPlayerStandard.setUp(
//            "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"
//            , JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, "嫂子闭眼睛"
//        )
//        jcVideoPlayerStandard.thumbImageView.setThumbInCustomProject("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640")
        }
        else{
            if(data.toString().startsWith("http")){
                videoView.setUp(data.toString(), JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, data.toString())
            }
            else{
                val filePath = getFilePathFromContentUri(data,contentResolver)
                Log.i(TAG,"文件路径${filePath}")
                videoView.setUp(filePath, JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, filePath)
            }
        }
    }


    /**
     * Gets the corresponding path to a file from the given content:// URI
     * @param selectedVideoUri The content:// URI to find the file path from
     * @param contentResolver The content resolver to use to perform the query.
     * @return the file path as a string
     */
    fun getFilePathFromContentUri(
        selectedVideoUri: Uri?,
        contentResolver: ContentResolver): String? {
        val filePath: String
        val filePathColumn = arrayOf(MediaColumns.DATA)
        val cursor: Cursor? =
            contentResolver.query(selectedVideoUri!!, filePathColumn, null, null, null)
        //      也可用下面的方法拿到cursor
//      Cursor cursor = this.context.managedQuery(selectedVideoUri, filePathColumn, null, null, null);
        cursor?.moveToFirst()
        val columnIndex: Int = cursor!!.getColumnIndex(filePathColumn[0])
        filePath = cursor.getString(columnIndex)
        cursor.close()
        return filePath
    }

    override fun onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        JCVideoPlayer.releaseAllVideos()
    }

}