package com.example.androiddevchallenge.ui

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.utils.getMutableStateOf


enum class ScreenName { HOME, DETAIL }

sealed class Screen(val id: ScreenName) {
    object Home : Screen(ScreenName.HOME)
    data class Detail(val petId: String) : Screen(ScreenName.DETAIL)
}


private const val SIS_SCREEN = "sis_screen"
private const val SIS_NAME = "screen_name"
private const val SIS_POST = "post"


private fun Screen.toBundle(): Bundle {
    return bundleOf(SIS_NAME to id.name).also {
        // add extra keys for various types here
        if (this is Screen.Detail) {
            it.putString(SIS_POST, petId)
        }
    }
}

private fun Bundle.toScreen(): Screen {
    val screenName = ScreenName.valueOf(getStringOrThrow(SIS_NAME))
    return when (screenName) {
        ScreenName.HOME -> Screen.Home
        //INTERESTS -> Interests
        ScreenName.DETAIL -> {
            val postId = getStringOrThrow(SIS_POST)
            Screen.Detail(postId)
        }
        else -> Screen.Home
    }
}


private fun Bundle.getStringOrThrow(key: String) =
    requireNotNull(getString(key)) { "Missing key '$key' in $this" }

class NavigationViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    var currentScreen: Screen by savedStateHandle.getMutableStateOf<Screen>(
        key = SIS_SCREEN,
        default = Screen.Home,
        save = { it.toBundle() },
        restore = { it.toScreen() }
    )
        private set // limit the writes to only inside this class.

    @MainThread
    fun onBack(): Boolean {
        val wasHandled = currentScreen != Screen.Home
        currentScreen = Screen.Home
        return wasHandled
    }

    @MainThread
    fun navigateTo(screen: Screen) {
        currentScreen = screen
    }
}