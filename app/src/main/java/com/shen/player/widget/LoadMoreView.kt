package com.shen.player.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.shen.player.R
import com.shen.player.model.HomeItemData
import kotlinx.android.synthetic.main.item_home.view.*


/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 11:03
 * 描述:该类主要作用
 */
class LoadMoreView:RelativeLayout {

    constructor(context: Context):super(context)
    constructor(context: Context,attrs:AttributeSet):super(context,attrs)
    constructor(context: Context,attrs: AttributeSet,defStyleAttr:Int):super(context,attrs,defStyleAttr)

    init {
        View.inflate(context,R.layout.load_more,this)
    }

}