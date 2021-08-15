package com.shen.player.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.shen.player.R
import com.shen.player.model.AudioBean
import kotlinx.android.synthetic.main.item_pop.view.*

/**
 * 作者:shenjianli
 * 创建时间： 2021/8/7 20:20
 * 描述:该类主要作用
 */
class PopListItemView: RelativeLayout {


    constructor(context: Context?):super(context)

    constructor(context: Context?,attrs:AttributeSet):super(context,attrs)

    constructor(context: Context?,attrs: AttributeSet,defStyleAttr:Int):super(context,attrs,defStyleAttr)


    init {
        View.inflate(context, R.layout.item_pop,this)
    }

    fun setData(data: AudioBean) {
        title.text = data.display_name
        artist.text = data.artst
    }

}