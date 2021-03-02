package com.example.androiddevchallenge.data.pets

import com.example.androiddevchallenge.model.BaseBean
import com.example.androiddevchallenge.model.PetBean

interface PetsRepository {

    suspend fun getPets(petId: String): BaseBean<PetBean>

    suspend fun getPets(): BaseBean<List<PetBean>>

}
