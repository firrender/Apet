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
package com.example.androiddevchallenge.data.pets.impl

import android.content.res.Resources
import com.example.androiddevchallenge.data.pets.PetsRepository
import com.example.androiddevchallenge.data.pets.pets
import com.example.androiddevchallenge.model.BaseBean
import com.example.androiddevchallenge.model.PetBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class FakePetsRepository(
    private val resources: Resources
) : PetsRepository {

    override suspend fun getPets(petId: String): BaseBean<PetBean> {
        return withContext(Dispatchers.IO) {
            val pet = pets.find { it.id == petId }
            if (pet == null) {
                BaseBean.Error(IllegalArgumentException("Post not found"))
            } else {
                BaseBean.Success(pet)
            }
        }
    }

    override suspend fun getPets(): BaseBean<List<PetBean>> {
        return withContext(Dispatchers.IO) {
            delay(200)
            if (shouldRandomlyFail()) {
                BaseBean.Error(IllegalStateException())
            } else {
                BaseBean.Success(pets)
            }
        }
    }

    private var requestCount = 0

    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0
}
