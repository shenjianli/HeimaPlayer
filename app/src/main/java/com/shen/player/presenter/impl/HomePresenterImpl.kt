package com.shen.player.presenter.impl

import com.shen.player.base.BaseListPresenter
import com.shen.player.base.BaseListView
import com.shen.player.model.HomeItemBean
import com.shen.player.model.HomeItemData
import com.shen.player.net.HomeRequest
import com.shen.player.net.ResponseHandler
import com.shen.player.presenter.interf.HomePresenter

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/5 18:52
 * 描述:该类主要作用
 */
class HomePresenterImpl(var homeView: BaseListView<List<HomeItemData>>):HomePresenter, ResponseHandler<HomeItemBean> {

    init {

    }

    override fun loadMoreData(page :Int) {
        HomeRequest(BaseListPresenter.LOAD_MORE_TYPE,page,this).execute()
    }

    override fun loadDatas() {
        HomeRequest(BaseListPresenter.INIT_TYPE,1,this).execute()
    }

    override fun destoryView() {
    }

    override fun onError(type:Int,msg: String?) {
        homeView.onError(msg)
    }

    override fun onSuccess(type: Int,result: HomeItemBean) {
        when(type){
            BaseListPresenter.INIT_TYPE ->{
                homeView.onSuccess(result.data)
            }
            BaseListPresenter.LOAD_MORE_TYPE ->{
                homeView.loadMore(result.data)
            }
        }
    }

}