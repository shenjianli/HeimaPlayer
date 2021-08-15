package com.shen.player.base

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shen.player.R
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/9 17:34
 * 描述:该类主要作用
 */
abstract class BaseListFragment<T,ITEMBEAN,ITEMVIEW:View> :BaseFragment(), BaseListView<T> {

    var page = 1

    val adapter by lazy { getListAdapter() }

    abstract fun getListAdapter():BaseListAdapter<ITEMBEAN,ITEMVIEW>

    val presenter by lazy { getListPresenter() }

    abstract fun getListPresenter():BaseListPresenter

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_list,null)
    }

    override fun initListener() {
        super.initListener()
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter
        refresh_layout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN)
        refresh_layout.setOnRefreshListener {
            page = 1
            presenter.loadDatas()
        }
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    val layoutManager = recycler_view.layoutManager
                    if (layoutManager is LinearLayoutManager){
                        val findLastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                        if(findLastVisibleItemPosition == adapter.itemCount - 1){
                            presenter.loadMoreData(page)
                        }
                    }
                }
            }
        })
    }

    override fun initData() {
        super.initData()
        presenter.loadDatas()
    }

    override fun loadMore(data: T?) {
        adapter.loadMore(getListData(data))
        page++
        refresh_layout.isRefreshing = false
    }

    override fun onError(message: String?) {
        myToast("加载失败$message")
        refresh_layout.isRefreshing = false
    }

    override fun onSuccess(data: T?) {
        adapter.updateList(getListData(data))
        refresh_layout?.isRefreshing = false
        page++
    }

    abstract fun getListData(data: T?): List<ITEMBEAN>?


    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destoryView()
    }

}