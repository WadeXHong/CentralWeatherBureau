package com.bardxhong.centralweatherbureau

import android.app.Application
import com.facebook.stetho.Stetho

class CBWApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
    }
}