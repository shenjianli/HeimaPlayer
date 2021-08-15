package com.shen.player.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shen.player.base.BaseListAdapter
import com.shen.player.model.HomeItemBean
import com.shen.player.model.HomeItemData
import com.shen.player.widget.HomeItemView
import com.shen.player.widget.LoadMoreView
import kotlinx.coroutines.newFixedThreadPoolContext

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 11:16
 * 描述:该类主要作用
 */
class HomeAdapter:BaseListAdapter<HomeItemData,HomeItemView>() {


    override fun getItemView(context: Context?): HomeItemView {
        return HomeItemView(context)
    }

    override fun refreshItemView(itemView: HomeItemView, data: HomeItemData) {
        itemView.setData(data)
    }

}