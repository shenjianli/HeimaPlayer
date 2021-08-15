package com.shen.player.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.shen.player.adapter.HomeAdapter
import com.shen.player.adapter.YDAdapter
import com.shen.player.base.BaseFragment
import com.shen.player.base.BaseListAdapter
import com.shen.player.base.BaseListFragment
import com.shen.player.base.BaseListPresenter
import com.shen.player.model.*
import com.shen.player.presenter.impl.MvListPresenterImpl
import com.shen.player.ui.activity.*
import com.shen.player.widget.HomeItemView
import com.shen.player.widget.YDItemView
import org.jetbrains.anko.support.v4.startActivity

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/9 19:06
 * 描述:该类主要作用
 */
class MvPagerFragment : BaseListFragment<YueDanBean, YueDanData, YDItemView>() {

    var type: String? = null;

    override fun init() {
        super.init()
        type = arguments?.getString("args")
    }

    override fun getListAdapter(): BaseListAdapter<YueDanData, YDItemView> {
        return YDAdapter()
    }

    override fun getListPresenter(): BaseListPresenter {
        return MvListPresenterImpl(type!!,this)
    }

    override fun getListData(data: YueDanBean?): List<YueDanData>? {
        return data?.data
    }

    override fun initListener() {
        super.initListener()
        adapter.setItemListener{
           println("it=$it")
            var imgUrl = ""
            if(it.images.size > 0){
                imgUrl = it.images[0]
            }
            val videoPlayerBean = VideoPlayerBean(it._id,it.title,it.videoUrl,imgUrl)
            startActivity<JiecaoVideoPlayerActivity>("item" to videoPlayerBean)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}