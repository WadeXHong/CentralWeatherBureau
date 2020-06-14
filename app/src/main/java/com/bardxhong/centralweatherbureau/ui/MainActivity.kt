package com.bardxhong.centralweatherbureau.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bardxhong.centralweatherbureau.R
import com.bardxhong.centralweatherbureau.data.ForecastItem
import com.bardxhong.centralweatherbureau.data.IItem
import com.bardxhong.centralweatherbureau.data.ImageItem
import com.bardxhong.centralweatherbureau.repo.FirstOpenSP
import com.bardxhong.centralweatherbureau.repo.ForecastRepo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private val TAG = this::class.java.name

    private val repo by lazy { ForecastRepo() }
    private val firstOpenSP by lazy { FirstOpenSP(this) }
    private val recyclerView by lazy { main_activity_recycler_view }
    private val itemClickListener = object : ForecastAdapter.onItemClickListener {
        override fun onDataViewHolderClick(item: IItem<*>) {
            startActivity(
                Intent(this@MainActivity, DetailActivity::class.java)
                    .putExtra(INTENT_EXTRA_KEY_FORECAST_ITEM, item)
            )
        }

        override fun onImageViewHolderClick(item: IItem<*>) {

        }
    }
    private val adapter by lazy {
        ForecastAdapter(arrayListOf()).apply { clickListener = itemClickListener }
    }

    private var hasAlreadyShown: Boolean = false

    // TODO: This could be extract in a base super class which makes anyone who whats IO coroutine to use
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        handleFirstOpen()

        // TODO refactor to MVP pattern
        launch {
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

    private fun handleFirstOpen() {
        if (!firstOpenSP.isFirstOpen) {
            if (!hasAlreadyShown) {
                hasAlreadyShown = true
                Toast.makeText(this,
                    R.string.toast_welcome_back, Toast.LENGTH_SHORT).show()
            }
        } else {
            firstOpenSP.isFirstOpen = false
        }
    }

    override fun onStop() {
        super.onStop()
        coroutineContext.cancel()
    }
}