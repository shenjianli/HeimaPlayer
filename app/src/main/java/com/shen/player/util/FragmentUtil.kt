package com.shen.player.util

import com.shen.player.R
import com.shen.player.base.BaseFragment
import com.shen.player.ui.fragment.HomeFragment
import com.shen.player.ui.fragment.MVFragment
import com.shen.player.ui.fragment.VBFragment
import com.shen.player.ui.fragment.YDFragment

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 10:46
 * 描述:该类主要作用
 */
class FragmentUtil private constructor(){
    val homeFragment by lazy { HomeFragment() }
    val mvFragment by lazy { MVFragment() }
    val vbFragment by lazy { VBFragment() }
    val ydFragment by lazy { YDFragment() }
    companion object{
        val fragmentUtil by lazy { FragmentUtil() }
    }

    fun getFragment(tabId:Int):BaseFragment?{
        when(tabId){
            R.id.tab_home -> return homeFragment
            R.id.tab_mv -> return mvFragment
            R.id.tab_vb -> return vbFragment
            R.id.tab_yd -> return ydFragment
        }
        return null
    }
}