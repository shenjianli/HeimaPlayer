package com.shen.player.widget

import android.content.Context
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.PopupWindow
import com.shen.player.R

/**
 * 作者:shenjianli
 * 创建时间： 2021/8/3 18:11
 * 描述:该类主要作用
 */
class PlayListPopWindow(context: Context,var adapter:BaseAdapter,var listener:AdapterView.OnItemClickListener,var window:Window):PopupWindow(){

    var alpha:Float = 0f

    init {

        alpha = window.attributes.alpha

        //设置布局
        val view = LayoutInflater.from(context).inflate(R.layout.pop_play_list,null,false)
        contentView = view
        //设置宽度和高度
        width = ViewGroup.LayoutParams.MATCH_PARENT

        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        manager.defaultDisplay.getSize(point)

        height = point.y*3/5

        isFocusable = true
        isOutsideTouchable = true
        setBackgroundDrawable(ColorDrawable())

        animationStyle = R.style.pop

        initView()

    }

    private fun initView() {
        val listView = contentView.findViewById<ListView>(R.id.song_list_view)
        listView.adapter = adapter
        listView.onItemClickListener = listener
    }


    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int, gravity: Int) {
        super.showAsDropDown(anchor, xoff, yoff, gravity)
        val attributes = window?.attributes;
        attributes.alpha = 0.3f
        window.attributes = attributes
    }


    override fun showAtLocation(parent: View?, gravity: Int, x: Int, y: Int) {
        super.showAtLocation(parent, gravity, x, y)
        val attributes = window?.attributes;
        attributes.alpha = 0.3f
        window.attributes = attributes
    }

    override fun dismiss() {
        super.dismiss()
        val attributes = window.attributes
        attributes.alpha = alpha
        window.attributes = attributes
    }

}