package com.bardxhong.centralweatherbureau.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bardxhong.centralweatherbureau.R
import com.bardxhong.centralweatherbureau.data.ForecastItem
import com.bardxhong.centralweatherbureau.data.IItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private val startTimeTextView by lazy { activity_detail_start_time }
    private val endTimeTextView by lazy { activity_detail_end_time }
    private val temperatureTextView by lazy { activity_detail_temperature }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        showForecastData(intent.getParcelableExtra<IItem<*>>(INTENT_EXTRA_KEY_FORECAST_ITEM))
    }

    private fun showForecastData(item: IItem<*>?) {
        (item as? ForecastItem)
            ?.data
            ?.let {
                startTimeTextView.text = it.startTime
                endTimeTextView.text = it.endTime
                temperatureTextView.text = it.detail.parameterName + it.detail.parameterUnit
            }
    }
}

const val INTENT_EXTRA_KEY_FORECAST_ITEM =
    "com.bardxhong.centralweatherbureau.ui.INTENT_EXTRA_KEY_FORECAST_ITEM"