package com.shen.player.presenter.impl

import com.shen.player.model.CateItemBean
import com.shen.player.net.MvCateRequest
import com.shen.player.net.ResponseHandler
import com.shen.player.presenter.interf.MvPresenter
import com.shen.player.view.MvView

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/9 18:40
 * 描述:该类主要作用
 */
class MvPresenterImpl(var mvView: MvView):MvPresenter, ResponseHandler<CateItemBean> {

    override fun loadDatas() {
        MvCateRequest(this).execute()
    }

    override fun onError(type: Int, msg: String?) {
        mvView.onError(msg)
    }

    override fun onSuccess(type: Int, result: CateItemBean) {

        mvView.onSuccess(result)
    }

}