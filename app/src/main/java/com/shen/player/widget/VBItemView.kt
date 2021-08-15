package com.shen.player.widget

import android.content.Context
import android.text.format.Formatter
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.shen.player.R
import com.shen.player.model.AudioBean
import kotlinx.android.synthetic.main.item_vb.view.*
import java.text.Format

/**
 * 作者:shenjianli
 * 创建时间： 2021/6/20 10:58
 * 描述:该类主要作用
 */
class VBItemView:RelativeLayout {

    constructor(context: Context?):super(context)
    constructor(context: Context?, attrs: AttributeSet):super(context,attrs)
    constructor(context: Context?, attrs: AttributeSet, defStyleAttr:Int):super(context,attrs,defStyleAttr)

    init {
        val inflate = View.inflate(context, R.layout.item_vb, this)
    }


    fun setData(itemBean: AudioBean) {
        itemBean?.let {
            title.text = itemBean.display_name
            artist.text = itemBean.artst
            size.text = Formatter.formatFileSize(context,itemBean.size)

        }
    }
}