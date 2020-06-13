package com.bardxhong.centralweatherbureau

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.name

    private val repo by lazy { ForecastRepo() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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