package com.shen.player.ui.activity

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.shen.player.R
import com.shen.player.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/4 15:47
 * 描述:该类主要作用
 */
class SplashActivity:BaseActivity(), ViewPropertyAnimatorListener {

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {
        super.initData()
        ViewCompat.animate(splash_iv)
            .scaleX(1.0f).scaleY(1.0f).setListener(this).duration = 3000

    }

    override fun initListener() {
        super.initListener()
    }

    override fun onAnimationEnd(view: View?) {
//        startActivity<MainActivity>()
//        finish()
        startActivityAndFinish<MainActivity>()
    }

    override fun onAnimationCancel(view: View?) {

    }

    override fun onAnimationStart(view: View?) {
    }

}