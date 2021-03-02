package com.example.androiddevchallenge.ui

import com.example.androiddevchallenge.data.AppContainer
import com.example.androiddevchallenge.data.pets.PetsRepository
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import com.example.androiddevchallenge.ui.detail.DetailPage
import com.example.androiddevchallenge.ui.home.HomePage
import com.example.androiddevchallenge.ui.theme.PetTheme

@Composable
fun PetsApp(
    appContainer: AppContainer,
    navigationViewModel: NavigationViewModel
) {
    PetTheme() {
        AppContent(
            navigationViewModel = navigationViewModel,
            petsRepository = appContainer.petsRepository
        )
    }
}

@Composable
private fun AppContent(
    navigationViewModel: NavigationViewModel,
    petsRepository: PetsRepository,
) {
    Crossfade(navigationViewModel.currentScreen) { screen ->
        when (screen) {
            is Screen.Home -> HomePage(
                navigateTo = navigationViewModel::navigateTo,
                petsRepository = petsRepository
            )
            is Screen.Detail -> DetailPage(
                petId = screen.petId,
                petsRepository = petsRepository,
                onBack = { navigationViewModel.onBack() }
            )
        }
    }

}
