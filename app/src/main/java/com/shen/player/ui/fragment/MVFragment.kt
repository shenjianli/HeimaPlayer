package com.shen.player.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.shen.player.R
import com.shen.player.adapter.MvPagerAdapter
import com.shen.player.base.BaseFragment
import com.shen.player.model.CateItemBean
import com.shen.player.presenter.impl.MvPresenterImpl
import com.shen.player.view.MvView
import kotlinx.android.synthetic.main.mv.*
import org.jetbrains.anko.textColor

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 10:38
 * 描述:该类主要作用
 */
class MVFragment:BaseFragment(),MvView {

    val presenter by lazy { MvPresenterImpl(this) }

    override fun initView(): View? {
        return View.inflate(context, R.layout.mv,null)
    }

    override fun initData() {
        super.initData()
        presenter.loadDatas()
    }

    override fun initListener() {
        super.initListener()
    }

    override fun onError(msg: String?) {
        myToast("加载失败")
    }

    override fun onSuccess(result: CateItemBean) {
        myToast("加载成功")
        val adpater = MvPagerAdapter(result?.data,childFragmentManager)
        viewPager.adapter = adpater
        tabLayout.setupWithViewPager(viewPager)
    }

}