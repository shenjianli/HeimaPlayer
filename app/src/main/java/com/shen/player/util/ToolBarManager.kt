package com.shen.player.util

import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.shen.player.R

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/4 17:16
 * 描述:该类主要作用
 */
interface ToolBarManager {
    val toolBar:Toolbar

    fun initMainToolbar(title:String){
        toolBar.title = title
        toolBar.inflateMenu(R.menu.main)
        toolBar.setOnMenuItemClickListener(object :Toolbar.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when(item?.itemId){
                    R.id.setting ->{
                        Toast.makeText(toolBar.context, "设置点击", Toast.LENGTH_SHORT).show()
                    }
                }
                return false;
            }

        })
    }
}