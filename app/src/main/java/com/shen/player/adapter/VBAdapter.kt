package com.shen.player.adapter

import android.content.Context
import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import androidx.cursoradapter.widget.CursorAdapter
import com.shen.player.model.AudioBean
import com.shen.player.widget.VBItemView
import kotlinx.android.synthetic.main.item_vb.view.*


/**
 * 作者:shenjianli
 * 创建时间： 2021/6/20 11:09
 * 描述:该类主要作用
 */
class VBAdapter(context: Context?,c:Cursor?)
    : CursorAdapter(context,c) {


    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return VBItemView(context)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val itemView = view as VBItemView
        val itemBean = AudioBean.getAduioBean(cursor)
        itemView.setData(itemBean)
    }
}