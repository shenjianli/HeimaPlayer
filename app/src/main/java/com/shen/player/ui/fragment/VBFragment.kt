package com.shen.player.ui.fragment

import android.content.AsyncQueryHandler
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.provider.MediaStore
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.shen.player.R
import com.shen.player.adapter.VBAdapter
import com.shen.player.base.BaseFragment
import com.shen.player.model.AudioBean
import com.shen.player.ui.activity.AudioPlayActivity
import com.shen.player.util.CursorUtil
import kotlinx.android.synthetic.main.fragment_vb.*
import org.jetbrains.anko.noButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.textColor
import org.jetbrains.anko.yesButton
import java.util.jar.Manifest

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 10:38
 * 描述:该类主要作用
 */
class VBFragment : BaseFragment() {

//    val handler = object: Handler(Looper.getMainLooper()){
//
//        override fun handleMessage(msg: Message) {
//            super.handleMessage(msg)
//            msg?.let {
//                val cursor = msg.obj as Cursor
//                CursorUtil.logCursor(cursor)
//
//            }
//        }
//    }

    override fun initView(): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_vb, null)
    }

    override fun initData() {
        super.initData()
        adapter = VBAdapter(context,null)
        list_view.adapter = adapter
        //开启线程
//        Thread(object :Runnable{
//            override fun run() {
//                //加载音乐数据
//                val resolver = context?.contentResolver
//                val query = resolver?.query(
//                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
//                    arrayOf(
//                        MediaStore.Audio.Media.DATA,
//                        MediaStore.Audio.Media.SIZE,
//                        MediaStore.Audio.Media.DISPLAY_NAME,
//                        MediaStore.Audio.Media.ARTIST
//                    ), null, null, null
//                )
//                //打印所有数据
//                query?.let {
//                    CursorUtil.logCursor(it)
//                }
//                val msg = Message.obtain()
//                msg.obj = query
//                handler?.sendMessage(msg)
//            }
//        }).start()

        //asyncTask
        //AudioTask().execute(context?.contentResolver)

        handlePermission()

    }

    private fun handlePermission() {
        val permission = android.Manifest.permission.READ_EXTERNAL_STORAGE
        val checkSelfPermission = ActivityCompat.checkSelfPermission(context!!, permission)
        if(checkSelfPermission == PackageManager.PERMISSION_GRANTED){
            loadAudio()
        }
        else{
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity!!,permission)){
                alert("我们只会访问音乐文件，不会访问隐私照片"){
                    yesButton {
                        goToRequestPermisson()
                    }
                    noButton {

                    }
                }
            }
            else{
                goToRequestPermisson()
            }
        }
    }

    private fun goToRequestPermisson() {
        val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        requestPermissions(permission,1)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                loadAudio()
            }
        }
    }

    fun loadAudio(){
        val handler = object : AsyncQueryHandler(context?.contentResolver) {

            override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
                super.onQueryComplete(token, cookie, cursor)
                CursorUtil.logCursor(cursor)
                (cookie as VBAdapter).swapCursor(cursor)
            }

        }

        val selection = String.format("%s > 1000",MediaStore.Audio.Media.SIZE)

        handler.startQuery(0,adapter,MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.SIZE,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.ARTIST
            ),selection,null,null)
    }


    class AudioTask : AsyncTask<ContentResolver, Void, Cursor>() {


        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: ContentResolver?): Cursor? {
            //加载音乐数据
            val resolver = params[0]
            val query = resolver?.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                arrayOf(
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.SIZE,
                    MediaStore.Audio.Media.DISPLAY_NAME,
                    MediaStore.Audio.Media.ARTIST
                ), null, null, null
            )
            //打印所有数据
            query?.let {
                CursorUtil.logCursor(it)
            }

            return query
        }

        override fun onPostExecute(result: Cursor?) {
            super.onPostExecute(result)
            result?.let {
                CursorUtil.logCursor(result)
            }
        }

    }

    var adapter :VBAdapter? = null
    override fun initListener() {
        super.initListener()
        list_view.setOnItemClickListener { parent, view, position, id ->

            val item = adapter?.getItem(position) as Cursor
            val list:ArrayList<AudioBean> = AudioBean.getAudioBeans(item)
            startActivity<AudioPlayActivity>("list" to list , "position" to position)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter?.swapCursor(null)
    }

}