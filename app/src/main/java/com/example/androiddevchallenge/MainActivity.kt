package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddevchallenge.ui.NavigationViewModel
import com.example.androiddevchallenge.ui.PetsApp

class MainActivity : AppCompatActivity() {

    private val navigationViewModel by viewModels<NavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as ApetApplication).container

        setContent {
            PetsApp(appContainer, navigationViewModel)
        }
    }

    override fun onBackPressed() {

        if (!navigationViewModel.onBack()) {

            super.onBackPressed()

        }
    }
}

