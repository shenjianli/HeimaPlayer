package com.shen.player.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shen.player.adapter.HomeAdapter
import com.shen.player.model.HomeItemData
import com.shen.player.widget.HomeItemView
import com.shen.player.widget.LoadMoreView

/**
 * 作者:shenjianli
 * 创建时间： 2021/5/9 17:46
 * 描述:该类主要作用
 */
abstract class BaseListAdapter<ITEMBEAN,ITEMVIEW:View>:RecyclerView.Adapter<BaseListAdapter.BaseListHolder>(){
    private var list = ArrayList<ITEMBEAN>()

    fun updateList(list:List<ITEMBEAN>?){
        list?.let {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun loadMore(list: List<ITEMBEAN>?){
        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    class BaseListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListHolder {
        if (viewType == 1){
            return BaseListHolder(LoadMoreView(parent.context))
        }
        return BaseListHolder(getItemView(parent?.context))
    }

    abstract fun getItemView(context: Context?): ITEMVIEW

    override fun getItemViewType(position: Int): Int {
        if (position == list.size){
            return 1
        }
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: BaseListHolder, position: Int) {
        if (position == list.size) return
        val data = list.get(position)
        val itemView = holder.itemView as ITEMVIEW
        refreshItemView(itemView,data)
        itemView.setOnClickListener {
//            itemClickListener?.let {
//                itemClickListener?.onItemClick(data)
//            }
            itemClickListener?.let {
                    it(data)
            }
            //itemClickListener?.invoke(data)
        }
    }

//    var itemClickListener:ItemClickListener<ITEMBEAN>? = null
//
//    interface ItemClickListener<ITEMBEAN>{
//        fun onItemClick(data:ITEMBEAN)
//    }
//
//    fun setClickListener(listener:ItemClickListener<ITEMBEAN>){
//        this.itemClickListener = listener
//    }

    var itemClickListener:((itemBean:ITEMBEAN)->Unit)? = null
    fun setItemListener(listener:(itemBean:ITEMBEAN) ->Unit){
        this.itemClickListener = listener
    }
    abstract fun refreshItemView(itemView: ITEMVIEW, data: ITEMBEAN)
}