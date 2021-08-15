package com.shen.player.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shen.player.ui.activity.MainActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/4 15:31
 * 描述:该类主要作用
 */
abstract class BaseActivity:AppCompatActivity(),AnkoLogger {

    protected val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initData()
        initListener()
    }

    abstract fun getLayoutId(): Int

    protected open fun initListener() {
    }

    protected open fun initData() {
    }

    protected fun myToast(msg:String){
        runOnUiThread {
            toast(msg)
        }
    }

    inline fun <reified T :BaseActivity> startActivityAndFinish(){
        startActivity<T>()
        finish()
    }

}