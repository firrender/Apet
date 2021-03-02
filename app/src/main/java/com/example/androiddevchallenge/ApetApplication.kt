package com.example.androiddevchallenge

import android.app.Application
import com.example.jetnews.data.AppContainer
import com.example.jetnews.data.AppContainerImpl

class ApetApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}