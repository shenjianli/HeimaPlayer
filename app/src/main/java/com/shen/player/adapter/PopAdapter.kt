package com.shen.player.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.shen.player.model.AudioBean
import com.shen.player.widget.PopListItemView
import kotlinx.coroutines.newFixedThreadPoolContext

/**
 * 作者:shenjianli
 * 创建时间： 2021/8/7 20:30
 * 描述:该类主要作用
 */
class PopAdapter(var list:List<AudioBean>):BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView:PopListItemView? = null
        if(convertView == null){
            itemView = PopListItemView(parent?.context)
        }
        else{
            itemView = convertView as PopListItemView
        }

        itemView.setData(list.get(position))

        return itemView
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}