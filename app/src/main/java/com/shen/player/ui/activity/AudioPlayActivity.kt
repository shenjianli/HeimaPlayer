package com.shen.player.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.SeekBar
import com.shen.player.R
import com.shen.player.adapter.PopAdapter
import com.shen.player.base.BaseActivity
import com.shen.player.model.AudioBean
import com.shen.player.model.HomeItemBean
import com.shen.player.service.AudioService
import com.shen.player.service.IService
import com.shen.player.util.StringUtil
import com.shen.player.widget.PlayListPopWindow
import kotlinx.android.synthetic.main.activity_audio_play.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.intentFor

/**
 * 作者:shenjianli
 * 创建时间： 2021/6/20 17:00
 * 描述:该类主要作用
 */
@Suppress("DEPRECATION")
class AudioPlayActivity: BaseActivity(), View.OnClickListener, AdapterView.OnItemClickListener {

    var audioBean:AudioBean? = null
    var duration:Int = 0
    override fun getLayoutId(): Int {
        return R.layout.activity_audio_play
    }

    val connection:AudioConnection by lazy { AudioConnection() }

    override fun initData() {
        super.initData()
//        val list = intent.getParcelableArrayListExtra<AudioBean>("list")
//        val position = intent.getIntExtra("position",-1)
//        Log.i(TAG,"收到的数据集合${list}")
//        Log.i(TAG,"点击的位置${position}")

        //通过AudioService来播放列表
        //val intent = Intent(this,AudioService::class.java)
        val intent = intent
        intent.setClass(this,AudioService::class.java)

//        intent.putExtra("list",list)
//        intent.putExtra("position",position)

        bindService(intent,connection,Context.BIND_AUTO_CREATE)

        startService(intent)

//        val mediaPlayer = MediaPlayer()
//        mediaPlayer.setOnPreparedListener {
//            mediaPlayer.start()
//        }
//        mediaPlayer.setDataSource(list?.get(position)?.data)
//        mediaPlayer.prepareAsync()
        EventBus.getDefault().register(this)
    }

    override fun initListener() {
        super.initListener()
        play.setOnClickListener(this)
        progress_seek_bar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(!fromUser){
                    return
                }
                iService?.seekTo(progress)
                startUpdateProgress()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        mode.setOnClickListener(this)

        last.setOnClickListener(this)
        next.setOnClickListener(this)

        list.setOnClickListener(this)

        lyric_view.setProgressListener {
            iService?.seekTo(it)
            updateProgress(it)
        }
    }


    var iService:IService? = null
    inner class AudioConnection:ServiceConnection{

        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iService = service as IService
        }

    }

    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onEvent(itemBean: AudioBean) {

        itemBean.display_name?.let {
            lyric_view.setSongName(it)
        }

        this.audioBean = itemBean
        audio_name.text = itemBean.display_name
        author_name.text = itemBean.artst


        updatePlayStateBtn()

        duration = iService?.getDuration() ?:0

        lyric_view.setSongDuration(duration)

        progress_seek_bar.max = duration

        startUpdateProgress()

        updatePlayModeBtn()

    }
    val handler = object :Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                MSG_PROGRESS ->startUpdateProgress()
            }
        }
    }
    val MSG_PROGRESS = 0
    private fun startUpdateProgress() {
        val progress:Int = iService?.getProgress() ?:0
        updateProgress(progress)
        handler.sendEmptyMessage(MSG_PROGRESS)
    }

    private fun updateProgress(progress: Int) {
        current_progress.text = StringUtil.parseDuration(progress)
        sum_progress.text = "/" + StringUtil.parseDuration(duration)
        progress_seek_bar.progress = progress

        lyric_view.updateProgress(progress)
    }


    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
        unbindService(connection)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.mode -> {
                updatePlayMode()
            }
            R.id.play -> updatePlayState()
            R.id.last -> iService?.playLast()
            R.id.next -> iService?.playNext()
            R.id.list -> showPlayList()
        }
    }

    private fun showPlayList() {

        val list = iService?.getPlayList()
        list?.let {
            val adapter = PopAdapter(it)
            val bottomH = btn_layout.height
            val popWindow = PlayListPopWindow(this,adapter,this,window)
            Log.i(TAG,"弹出窗口：$bottomH" )
            popWindow.showAtLocation(btn_layout, Gravity.BOTTOM,0,0)
        }

    }

    private fun updatePlayMode() {
        iService?.updatePlayMode()
        updatePlayModeBtn()
    }

    private fun updatePlayModeBtn() {
        iService?.let {
            val playMode:Int = it.getPlayMode()
            when(playMode){
                AudioService.MODE_ALL -> mode.text = "全部"
                AudioService.MODE_SINGLE -> mode.text = "单曲"
                AudioService.MODE_RANDOM -> mode.text = "随机"
            }
        }
    }

    private fun updatePlayState() {
        iService?.updatePlayState()

        updatePlayStateBtn()
    }

    private fun updatePlayStateBtn() {
        val isPlaying = iService?.isPlaying()
        isPlaying?.let {
            if(isPlaying){
                play.text = "暂停"
                handler.removeMessages(MSG_PROGRESS)
            }
            else{
                play.text = "播放"
                handler.sendEmptyMessage(MSG_PROGRESS)
            }
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        iService?.playPosition(position)
    }
}