package com.example.androiddevchallenge.model

import androidx.annotation.DrawableRes

data class PetBean(
    val id: String,
    val title: String,
    val subtitle: String? = null,
    val name: String,
    val age: Int,
    val sex: String,
    val userName: String,
    val putTime: String,
    val like: String,
    @DrawableRes val imagePetId: Int,
    @DrawableRes val imageUserId: Int
)