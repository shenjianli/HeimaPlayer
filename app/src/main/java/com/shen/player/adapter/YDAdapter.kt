package com.shen.player.adapter

import android.content.Context
import com.shen.player.base.BaseListAdapter
import com.shen.player.model.YueDanData
import com.shen.player.widget.YDItemView

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 11:16
 * 描述:该类主要作用
 */
class YDAdapter:BaseListAdapter<YueDanData,YDItemView>() {

    override fun getItemView(context: Context?): YDItemView {
        return YDItemView(context)
    }

    override fun refreshItemView(itemView: YDItemView, data: YueDanData) {
        itemView.setData(data)
    }

}