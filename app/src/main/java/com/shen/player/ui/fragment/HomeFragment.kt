package com.shen.player.ui.fragment

import com.shen.player.adapter.HomeAdapter
import com.shen.player.base.BaseListAdapter
import com.shen.player.base.BaseListFragment
import com.shen.player.base.BaseListPresenter
import com.shen.player.model.HomeItemData
import com.shen.player.presenter.impl.HomePresenterImpl
import com.shen.player.widget.HomeItemView

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 10:38
 * 描述:该类主要作用
 */
class HomeFragment: BaseListFragment<List<HomeItemData>,HomeItemData,HomeItemView>(){


    override fun getListAdapter(): BaseListAdapter<HomeItemData, HomeItemView> {
        return HomeAdapter()
    }

    override fun getListPresenter(): BaseListPresenter {
        return HomePresenterImpl(this)
    }

    override fun getListData(data: List<HomeItemData>?): List<HomeItemData>? {
        return data
    }

}
