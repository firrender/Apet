package com.example.androiddevchallenge.data.pets.impl

import com.example.androiddevchallenge.data.pets.PetsRepository
import android.content.res.Resources
import com.example.androiddevchallenge.data.pets.pets
import com.example.androiddevchallenge.model.BaseBean
import com.example.androiddevchallenge.model.PetBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@OptIn(ExperimentalCoroutinesApi::class)
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
