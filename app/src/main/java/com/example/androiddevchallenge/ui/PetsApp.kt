/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import com.example.androiddevchallenge.data.AppContainer
import com.example.androiddevchallenge.data.pets.PetsRepository
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
