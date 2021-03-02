package com.example.androiddevchallenge.data.pets.impl

import PetsRepository
import android.content.Context
import com.example.androiddevchallenge.data.pets.pets
import com.example.androiddevchallenge.model.BaseBean
import com.example.androiddevchallenge.model.PetBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext

@OptIn(ExperimentalCoroutinesApi::class)
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
