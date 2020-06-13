package com.bardxhong.centralweatherbureau.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bardxhong.centralweatherbureau.R
import com.bardxhong.centralweatherbureau.data.ForecastItem
import com.bardxhong.centralweatherbureau.data.IItem

class ForecastAdapter(private val dataList: ArrayList<IItem>) :
    RecyclerView.Adapter<ForecastViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
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

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position % 2 == 0 -> ITEM_VIEW_TYPE_DATA
            else -> ITEM_VIEW_TYPE_IMAGE
        }
    }
}

abstract class ForecastViewHolder(view: View): RecyclerView.ViewHolder(view) {
    abstract fun onBind(item: IItem)
}

class ForecastDataViewHolder(view: View) : ForecastViewHolder(view) {
    private val startTimeTextView by lazy { itemView.findViewById<TextView>(R.id.item_view_data_start_time) }
    private val endTimeTextView by lazy { itemView.findViewById<TextView>(R.id.item_view_data_end_time) }
    private val temperature by lazy { itemView.findViewById<TextView>(R.id.item_view_data_temperature) }
    override fun onBind(item: IItem) {
        if (item is ForecastItem) {
            startTimeTextView.text = item.data.startTime
            endTimeTextView.text = item.data.endTime
            temperature.text = item.data.detail.parameterName + item.data.detail.parameterUnit
        }
    }
}

class ForecastImageViewHolder(view: View) : ForecastViewHolder(view) {
    override fun onBind(item: IItem) {}
}
