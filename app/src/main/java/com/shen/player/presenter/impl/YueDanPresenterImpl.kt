package com.shen.player.presenter.impl

import com.shen.player.base.BaseListPresenter
import com.shen.player.base.BaseListView
import com.shen.player.model.YueDanBean
import com.shen.player.net.ResponseHandler
import com.shen.player.net.YueDanRequest
import com.shen.player.presenter.interf.YueDanPresenter

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/9 13:48
 * 描述:该类主要作用
 */
class YueDanPresenterImpl(var yueDanView: BaseListView<YueDanBean>):YueDanPresenter, ResponseHandler<YueDanBean> {

    override fun loadMoreData(page: Int) {
        YueDanRequest(BaseListPresenter.LOAD_MORE_TYPE,"android",page,this).execute()
    }

    override fun loadDatas() {
        YueDanRequest(BaseListPresenter.INIT_TYPE,"android",1,this).execute()
    }

    override fun destoryView() {

    }

    override fun onError(type: Int, msg: String?) {
        yueDanView.onError(msg)
    }

    override fun onSuccess(type: Int, result: YueDanBean) {
        if (type == BaseListPresenter.INIT_TYPE){
            yueDanView.onSuccess(result)
        }
        else if(type == BaseListPresenter.LOAD_MORE_TYPE){
            yueDanView.loadMore(result)
        }
    }
}