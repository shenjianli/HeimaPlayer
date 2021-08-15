package com.shen.player.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.shen.player.R
import com.shen.player.model.AudioBean
import com.shen.player.notify.NotificationTest
import com.shen.player.ui.activity.AudioPlayActivity
import com.shen.player.ui.activity.MainActivity
import com.shen.player.ui.activity.SplashActivity
import org.greenrobot.eventbus.EventBus
import kotlin.random.Random as Random

/**
 * 作者:shenjianli
 * 创建时间： 2021/6/20 18:24
 * 描述:该类主要作用
 */
class AudioService : Service() {

    var list: ArrayList<AudioBean>? = null
    var position: Int = -1
    val mediaPlayer by lazy { MediaPlayer() }

    val FROM_PRE = 1
    val FROM_NEXT = 2
    val FROM_STATE = 3
    val FROM_CONTENT = 4

    val binder by lazy { AudioBinder() }

    companion object{
        val MODE_ALL = 1
        val MODE_SINGLE = 2
        val MODE_RANDOM = 3
    }

    var mode:Int = MODE_ALL

    val sp by lazy { getSharedPreferences("config",Context.MODE_PRIVATE) }

    override fun onCreate() {
        super.onCreate()
        mode = sp.getInt("mode", MODE_ALL)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val from = intent?.getIntExtra("from",-1)
        when(from){
            FROM_PRE -> {
                binder.playLast()
            }
            FROM_STATE -> {
                binder.updatePlayState()
            }
            FROM_NEXT -> {
                binder.playNext()
            }
            FROM_CONTENT ->{
                binder.notifyUpdateUi()
            }
            else -> {
                list = intent?.getParcelableArrayListExtra<AudioBean>("list")
                val newPosition = intent?.getIntExtra("position", 0) ?: 0
                if(newPosition != position){
                    position = newPosition
                    binder.playItem()
                }
                else{
                    binder.notifyUpdateUi()
                }
            }
        }

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }


    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    inner class AudioBinder : Binder(), MediaPlayer.OnPreparedListener,IService {

        override fun playItem() {

            mediaPlayer.setOnPreparedListener(this)
            mediaPlayer.setOnCompletionListener {
                autoPlayNext()
            }
            mediaPlayer.reset()
            mediaPlayer.setDataSource(list?.get(position)?.data)
            mediaPlayer.prepareAsync()
        }

        private fun autoPlayNext() {
            when(mode){
                MODE_ALL ->{
                    list.let {
                        if (it != null) {
                            position = (position + 1) % it.size
                        }
                    }
                }
                MODE_SINGLE ->{}
                MODE_RANDOM ->{
                    list?.let {
                        position = java.util.Random().nextInt(it.size)
                    }

                }
            }
            playItem()
        }

        //更新播放状态
        override fun updatePlayState() {
            val isPlaying = isPlaying()
            isPlaying?.let {
                if(isPlaying){
                    pause()
                }
                else{
                   start()
                }
            }
        }

        fun start(){
            mediaPlayer?.start()
            EventBus.getDefault().post(list?.get(position))
            notification1?.contentView?.setTextViewText(R.id.state,"暂停")
            manager?.notify(1,notification1)
        }

        fun pause(){
            mediaPlayer?.pause()
            EventBus.getDefault().post(list?.get(position))
            notification1?.contentView?.setTextViewText(R.id.state,"播放")
            manager?.notify(1,notification1)
        }

        override fun isPlaying():Boolean{
            return  mediaPlayer?.isPlaying
        }

        override fun getDuration(): Int {
            return mediaPlayer?.duration ?:0
        }

        override fun getProgress(): Int {
            return mediaPlayer?.currentPosition
        }

        override fun seekTo(progress: Int) {
            mediaPlayer?.seekTo(progress)
        }

        override fun updatePlayMode() {
            when(mode){
                MODE_ALL -> mode = MODE_SINGLE
                MODE_SINGLE -> mode = MODE_RANDOM
                MODE_RANDOM -> mode = MODE_ALL
            }
            sp.edit().putInt("mode",mode).commit()
        }

        override fun getPlayMode(): Int {
            return mode
        }

        override fun playLast() {
            list?.let {
                when(mode){
                    MODE_RANDOM -> {
                        position = java.util.Random().nextInt(it.size-1)
                    }
                    else -> {
                        if(position == 0){
                            position = it.size -1
                        }
                        else{
                            position--
                        }
                    }
                }
                playItem()
            }

        }

        override fun playNext() {
            list?.let {
                when(mode){
                    MODE_RANDOM -> {
                        position = java.util.Random().nextInt(it.size -1)
                    }
                    else ->{
                        position = (position + 1) % it.size
                    }
                }
                playItem()
            }
        }

        override fun getPlayList(): List<AudioBean>? {
            return list
        }

        override fun playPosition(position: Int) {
            this@AudioService.position = position
            playItem()
        }

        override fun onPrepared(mp: MediaPlayer?) {
            mediaPlayer.start()
            notifyUpdateUi()
            showNotification()
        }

        fun notifyUpdateUi() {
            EventBus.getDefault().post(list?.get(position))
        }

    }

    var manager:NotificationManager? = null
    var notification1:Notification? = null
    private fun showNotification() {
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //需添加的代码
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelId = "default";
            val channelName = "默认通知";
            manager?.createNotificationChannel(NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH));
        }

        notification1 = getNotification()

        manager?.notify(1,notification1)
        Log.i("AudioService","显示通知")
    }

    private fun getNotification(): Notification?{
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.tab_shop)
        val notification = NotificationCompat.Builder(this,"default")
            .setTicker(list?.get(position)?.display_name)
            .setSmallIcon(R.mipmap.tab_mine)
//            .setLargeIcon(bitmap)
//            .setContentTitle("北京")
//            .setContentText("汪峰")
            .setCustomContentView(getRemoteViews())
            .setWhen(System.currentTimeMillis())
            .setOngoing(true)//不能滑动删除
            .setContentIntent(getPendingIntent())
            .build()

        return notification
    }

    private fun getRemoteViews(): RemoteViews? {
        val remoteViews = RemoteViews(packageName,R.layout.notification)
        remoteViews.setTextViewText(R.id.title,list?.get(position)?.display_name )
        remoteViews.setTextViewText(R.id.artist,list?.get(position)?.artst )
        remoteViews.setOnClickPendingIntent(R.id.pre,getPrePedingIntent())
        remoteViews.setOnClickPendingIntent(R.id.state,getStatePedingIntent())
        remoteViews.setOnClickPendingIntent(R.id.next,getNextPedingIntent())

        return remoteViews
    }

    private fun getNextPedingIntent(): PendingIntent? {
        val intent = Intent(this, AudioService::class.java)
        intent.putExtra("from",FROM_NEXT)
        val pendingIntent = PendingIntent.getService(this,4,intent, PendingIntent.FLAG_UPDATE_CURRENT)
        return pendingIntent
    }

    private fun getStatePedingIntent(): PendingIntent? {
        val intent = Intent(this, AudioService::class.java)
        intent.putExtra("from",FROM_STATE)
        val pendingIntent = PendingIntent.getService(this,3,intent, PendingIntent.FLAG_UPDATE_CURRENT)
        return pendingIntent
    }

    private fun getPrePedingIntent(): PendingIntent? {
        val intent = Intent(this, AudioService::class.java)
        intent.putExtra("from",FROM_PRE)
        val pendingIntent = PendingIntent.getService(this,2,intent, PendingIntent.FLAG_UPDATE_CURRENT)
        return pendingIntent
    }

    private fun getPendingIntent(): PendingIntent? {
        val splashIntent = Intent(this, SplashActivity::class.java)
        val intentM = Intent(this, MainActivity::class.java)
        val intent = Intent(this, AudioPlayActivity::class.java)
        intent.putExtra("from",FROM_CONTENT)
        val intents = arrayOf(splashIntent,intentM,intent)
        val pendingIntent = PendingIntent.getActivities(this,1,intents, PendingIntent.FLAG_UPDATE_CURRENT)
        return pendingIntent
    }

    fun hide(view: View){
        manager?.cancel(1)
    }

}