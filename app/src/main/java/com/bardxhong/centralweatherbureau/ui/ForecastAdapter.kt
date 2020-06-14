package com.bardxhong.centralweatherbureau.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bardxhong.centralweatherbureau.R
import com.bardxhong.centralweatherbureau.data.ForecastItem
import com.bardxhong.centralweatherbureau.data.IItem

class ForecastAdapter(private val dataList: ArrayList<IItem<*>>) :
    RecyclerView.Adapter<ForecastViewHolder>() {

    private val ITEM_VIEW_TYPE_DATA = 0
    private val ITEM_VIEW_TYPE_IMAGE = 1
    var clickListener: onItemClickListener? = null

    // TODO might has better solution
    fun add(itemList: List<IItem<*>>) {
        dataList.addAll(itemList)
        notifyDataSetChanged()
    }

    fun clear() {
        dataList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return LayoutInflater.from(parent.context).let { inflater ->
            when (viewType) {
                ITEM_VIEW_TYPE_DATA ->
                    ForecastDataViewHolder(
                        inflater.inflate(
                            R.layout.item_view_data,
                            parent,
                            false
                        ).also {
                            it.setOnClickListener {

                            }
                        }
                    )
                ITEM_VIEW_TYPE_IMAGE ->
                    ForecastImageViewHolder(
                        inflater.inflate(
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

    interface onItemClickListener {
        fun onDataViewHolderClick(item: IItem<*>)
        fun onImageViewHolderClick(item: IItem<*>)
    }

    inner class ForecastDataViewHolder(view: View) : ForecastViewHolder(view) {
        init {
            itemView.setOnClickListener {
                clickListener?.onDataViewHolderClick(dataList[layoutPosition])
            }
        }

        private val startTimeTextView by lazy { itemView.findViewById<TextView>(R.id.item_view_data_start_time) }
        private val endTimeTextView by lazy { itemView.findViewById<TextView>(R.id.item_view_data_end_time) }
        private val temperature by lazy { itemView.findViewById<TextView>(R.id.item_view_data_temperature) }
        override fun onBind(item: IItem<*>) {
            if (item is ForecastItem) {
                startTimeTextView.text = item.data.startTime
                endTimeTextView.text = item.data.endTime
                temperature.text = item.data.detail.parameterName + item.data.detail.parameterUnit
            }
        }
    }

    inner class ForecastImageViewHolder(view: View) : ForecastViewHolder(view) {
        init {
            itemView.setOnClickListener {
                clickListener?.onImageViewHolderClick(dataList[layoutPosition])
            }
        }

        override fun onBind(item: IItem<*>) {}
    }

}

abstract class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun onBind(item: IItem<*>)
}
