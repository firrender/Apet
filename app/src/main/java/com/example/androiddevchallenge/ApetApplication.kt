package com.example.androiddevchallenge

import com.example.androiddevchallenge.data.AppContainer
import com.example.androiddevchallenge.data.AppContainerImpl
import android.app.Application

class ApetApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}