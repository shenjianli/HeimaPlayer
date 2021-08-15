package com.shen.player.ui.fragment

import com.shen.player.adapter.YDAdapter
import com.shen.player.base.BaseListAdapter
import com.shen.player.base.BaseListFragment
import com.shen.player.base.BaseListPresenter
import com.shen.player.model.YueDanBean
import com.shen.player.model.YueDanData
import com.shen.player.presenter.impl.YueDanPresenterImpl
import com.shen.player.widget.YDItemView

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 10:38
 * 描述:该类主要作用
 */
class YDFragment: BaseListFragment<YueDanBean,YueDanData,YDItemView>() {
    override fun getListAdapter(): BaseListAdapter<YueDanData, YDItemView> {
        return YDAdapter()
    }

    override fun getListPresenter(): BaseListPresenter {
        return YueDanPresenterImpl(this)
    }

    override fun getListData(data: YueDanBean?): List<YueDanData>? {
        return data?.data
    }

}