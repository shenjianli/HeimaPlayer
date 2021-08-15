package com.shen.player.presenter.impl

import com.shen.player.base.BaseListPresenter
import com.shen.player.base.BaseListView
import com.shen.player.constant.Constant
import com.shen.player.model.HomeItemBean
import com.shen.player.model.YueDanBean
import com.shen.player.net.MvListRequest
import com.shen.player.net.ResponseHandler
import com.shen.player.presenter.interf.MvListPresenter
import com.shen.player.view.MvListView

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/16 16:32
 * 描述:该类主要作用
 */
class MvListPresenterImpl(var type:String, var mvListView:BaseListView<YueDanBean>?):
    MvListPresenter, ResponseHandler<YueDanBean> {

    override fun loadMoreData(page: Int) {
        MvListRequest(BaseListPresenter.LOAD_MORE_TYPE,type,page,this).execute()
    }

    override fun loadDatas() {
        MvListRequest(BaseListPresenter.INIT_TYPE,type,0,this).execute()
    }

    override fun destoryView() {
        if(mvListView != null){
            mvListView = null
        }
    }

    override fun onError(type: Int, msg: String?) {
        mvListView?.onError(msg)
    }

    override fun onSuccess(type: Int, result: YueDanBean) {

        result?.let {
            for (data in it.data){
                val randoms = (Constant.videoArray.indices).random()
                data.videoUrl = Constant.videoArray[randoms]
            }
        }

        if(type == BaseListPresenter.INIT_TYPE){
            mvListView?.onSuccess(result)
        }
        else{
            mvListView?.loadMore(result)
        }
    }
}