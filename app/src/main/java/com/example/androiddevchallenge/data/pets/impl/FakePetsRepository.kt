package com.example.androiddevchallenge.data.pets.impl

import PetsRepository
import android.content.res.Resources
import com.example.androiddevchallenge.data.pets.pets
import com.example.androiddevchallenge.model.BaseBean
import com.example.androiddevchallenge.model.PetBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * Implementation of PostsRepository that returns a hardcoded list of
 * posts with resources after some delay in a background thread.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class FakePetsRepository(
    private val resources: Resources
) : PetsRepository {

    // for now, store these in memory
    private val favorites = MutableStateFlow<Set<String>>(setOf())

    // Used to make suspend functions that read and update state safe to call from any thread
    private val mutex = Mutex()

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
            delay(800) // pretend we're on a slow network
            if (shouldRandomlyFail()) {
                BaseBean.Error(IllegalStateException())
            } else {
                BaseBean.Success(pets)
            }
        }
    }

    // used to drive "random" failure in a predictable pattern, making the first request always
    // succeed
    private var requestCount = 0

    /**
     * Randomly fail some loads to simulate a real network.
     *
     * This will fail deterministically every 5 requests
     */
    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0
}
