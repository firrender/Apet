package com.example.androiddevchallenge

import AppContainer
import AppContainerImpl
import android.app.Application

class ApetApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}