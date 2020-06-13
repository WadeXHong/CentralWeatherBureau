package com.bardxhong.centralweatherbureau.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bardxhong.centralweatherbureau.R
import com.bardxhong.centralweatherbureau.data.IItem

class ForecastAdapter(private val dataList: ArrayList<IItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val ITEM_VIEW_TYPE_DATA = 0
    private val ITEM_VIEW_TYPE_IMAGE = 1

    // TODO might has better solution
    fun add(itemList: List<IItem>) {
        dataList.addAll(itemList)
        notifyDataSetChanged()
    }

    fun clear() {
        dataList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LayoutInflater.from(parent.context).let {
            when (viewType) {
                ITEM_VIEW_TYPE_DATA -> ForecastDataViewHolder(
                    it.inflate(
                        R.layout.item_view_data,
                        parent,
                        false
                    )
                )
                ITEM_VIEW_TYPE_IMAGE -> ForecastImageViewHolder(
                    it.inflate(
                        R.layout.item_view_image,
                        parent,
                        false
                    )
                )
                else -> throw IllegalStateException("wtf")
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position % 2 == 0 -> ITEM_VIEW_TYPE_IMAGE
            else -> ITEM_VIEW_TYPE_DATA
        }
    }
}

class ForecastDataViewHolder(view: View) : RecyclerView.ViewHolder(view)
class ForecastImageViewHolder(view: View) : RecyclerView.ViewHolder(view)
