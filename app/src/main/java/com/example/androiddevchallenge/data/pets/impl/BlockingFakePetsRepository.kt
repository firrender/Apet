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

import android.content.Context
import com.example.androiddevchallenge.data.pets.PetsRepository
import com.example.androiddevchallenge.data.pets.pets
import com.example.androiddevchallenge.model.BaseBean
import com.example.androiddevchallenge.model.PetBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BlockingFakePetsRepository(private val context: Context) : PetsRepository {

    override suspend fun getPets(petId: String): BaseBean<PetBean> {
        return withContext(Dispatchers.IO) {
            val post = pets.find { it.id == petId }
            if (post == null) {
                BaseBean.Error(IllegalArgumentException("Unable to find post"))
            } else {
                BaseBean.Success(post)
            }
        }
    }

    override suspend fun getPets(): BaseBean<List<PetBean>> {
        return BaseBean.Success(pets)
    }
}
