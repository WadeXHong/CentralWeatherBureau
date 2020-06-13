package com.bardxhong.centralweatherbureau

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bardxhong.centralweatherbureau.ui.ForecastAdapter
import com.bardxhong.centralweatherbureau.data.ForecastItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.name

    private val repo by lazy { ForecastRepo() }

    private val recyclerView by lazy { main_activity_recycler_view }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = ForecastAdapter(
            arrayListOf(
                ForecastItem(),
                ForecastItem(),
                ForecastItem(),
                ForecastItem(),
                ForecastItem()
            ))
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        // TODO refactor to MVP pattern
        CoroutineScope(Dispatchers.IO).launch {
            val res = repo.get36HoursForecast(
                // TODO location to Enum
                locationNames = listOf("臺北市"),
                // TODO elementName to Enum
                elementNames = listOf("MinT")
            )
            Log.i(TAG, res.body().toString())
        }
    }
}