package com.bardxhong.centralweatherbureau

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bardxhong.centralweatherbureau.data.IItem

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        intent.getParcelableExtra<IItem<*>>(INTENT_EXTRA_KEY_FORECAST_ITEM)
    }
}

const val INTENT_EXTRA_KEY_FORECAST_ITEM = "com.bardxhong.centralweatherbureau.INTENT_EXTRA_KEY_FORECAST_ITEM"