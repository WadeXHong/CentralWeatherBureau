package com.bardxhong.centralweatherbureau

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bardxhong.centralweatherbureau.data.ForecastItem
import com.bardxhong.centralweatherbureau.data.IItem
import com.bardxhong.centralweatherbureau.data.ImageItem
import com.bardxhong.centralweatherbureau.ui.ForecastAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.name

    private val repo by lazy { ForecastRepo() }

    private val recyclerView by lazy { main_activity_recycler_view }

    private val itemClickListener = object :ForecastAdapter.onItemClickListener {
        override fun onDataViewHolderClick(item: IItem<*>) {
            // TODO startIntent
        }

        override fun onImageViewHolderClick(item: IItem<*>) {

        }
    }

    private val adapter by lazy {
        ForecastAdapter(arrayListOf()).apply { clickListener = itemClickListener }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = adapter
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

            res.body()
                ?.records
                ?.location
                ?.firstOrNull()
                ?.weatherElementList
                ?.firstOrNull()
                ?.intervalDataList
                ?.let { intervalList ->
                    val list = mutableListOf<IItem<*>>()
                    intervalList.forEach {
                        list.add(ForecastItem(it))
                        list.add(ImageItem(it))
                    }
                    withContext(Dispatchers.Main) {
                        adapter.add(list)
                    }
                }
            Log.i(TAG, res.body().toString())
        }
    }
}