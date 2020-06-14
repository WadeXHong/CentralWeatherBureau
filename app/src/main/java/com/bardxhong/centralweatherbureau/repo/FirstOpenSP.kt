package com.bardxhong.centralweatherbureau.repo

import android.content.Context

class FirstOpenSP(context: Context) {

    private val pref =
        context.getSharedPreferences(
            SHARED_PREFERENCE_NAME_FIRST_OPEN, Context.MODE_PRIVATE
        )

    var isFirstOpen: Boolean
        set(value) = pref.edit().putBoolean(SHARED_PREFERENCE_KEY_FIRST_OPEN, value).apply()
        get() = pref.getBoolean(SHARED_PREFERENCE_KEY_FIRST_OPEN, true)
}

const val SHARED_PREFERENCE_NAME_FIRST_OPEN =
    "com.bardxhong.centralweatherbureau.repo.SHARED_PREFERENCE_NAME_FIRST_OPEN"
private const val SHARED_PREFERENCE_KEY_FIRST_OPEN =
    "com.bardxhong.centralweatherbureau.repo.SHARED_PREFERENCE_KEY_FIRST_OPEN"