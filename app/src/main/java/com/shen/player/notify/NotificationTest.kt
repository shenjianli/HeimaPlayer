package com.shen.player.notify

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.RemoteViews
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.shen.player.R
import com.shen.player.ui.activity.MainActivity

/**
 * 作者:shenjianli
 * 创建时间： 2021/8/8 10:28
 * 描述:该类主要作用
 */
class NotificationTest :AppCompatActivity(){




    var manager:NotificationManager? = null


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        val args = intent.getStringExtra("args")
        args?.let {
            Toast.makeText(this,args,Toast.LENGTH_SHORT).show()
        }
    }



    fun show(view: View){
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager?.notify(1,getNotification())
    }


    fun hide(view:View){
        manager?.cancel(1)
    }

    private fun getNotification():Notification?{
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.tab_shop)
        val notification = NotificationCompat.Builder(this,"audio_play")
            .setTicker("正在播放歌曲北京")
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
        remoteViews.setTextViewText(R.id.title,"北京" )
        remoteViews.setTextViewText(R.id.artist,"汪峰" )
        remoteViews.setOnClickPendingIntent(R.id.pre,getPrePedingIntent())
        remoteViews.setOnClickPendingIntent(R.id.state,getStatePedingIntent())
        remoteViews.setOnClickPendingIntent(R.id.next,getNextPedingIntent())

        return remoteViews
    }

    private fun getNextPedingIntent(): PendingIntent? {
        val intent = Intent(this,NotificationTest::class.java)
        intent.putExtra("args","从通知栏下一曲点击进来")
        val pendingIntent = PendingIntent.getActivity(this,4,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        return pendingIntent
    }

    private fun getStatePedingIntent(): PendingIntent? {
        val intent = Intent(this,NotificationTest::class.java)
        intent.putExtra("args","从通知栏播放状态点击进来")
        val pendingIntent = PendingIntent.getActivity(this,3,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        return pendingIntent
    }

    private fun getPrePedingIntent(): PendingIntent? {
        val intent = Intent(this,NotificationTest::class.java)
        intent.putExtra("args","从通知栏上一曲点击进来")
        val pendingIntent = PendingIntent.getActivity(this,2,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        return pendingIntent
    }

    private fun getPendingIntent(): PendingIntent? {
        val intent = Intent(this,NotificationTest::class.java)
        intent.putExtra("args","从通知栏主体点击进来")
        val pendingIntent = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        return pendingIntent
    }









}