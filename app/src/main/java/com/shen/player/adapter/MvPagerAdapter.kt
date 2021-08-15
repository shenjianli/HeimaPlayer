package com.shen.player.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.shen.player.model.CateItemData
import com.shen.player.ui.fragment.MvPagerFragment
import androidx.fragment.app.FragmentPagerAdapter as FragmentPagerAdapter

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/9 19:02
 * 描述:该类主要作用
 */
class MvPagerAdapter(val list:List<CateItemData>,fm:FragmentManager): FragmentPagerAdapter(fm) {




    override fun getItem(position: Int): Fragment {
        val fragment = MvPagerFragment()
        val bundle = Bundle()
        bundle.putString("args",list?.get(position)?.type)
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int {
        return list?.size?:0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        super.getPageTitle(position)
        return list?.get(position)?.title
    }
}