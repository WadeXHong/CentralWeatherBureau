package com.bardxhong.centralweatherbureau.data

import android.os.Parcelable

interface IItem<T>: Parcelable {
    val data: T
}