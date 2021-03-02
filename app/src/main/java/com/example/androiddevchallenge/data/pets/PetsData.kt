package com.example.androiddevchallenge.data.pets

import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.PetBean

/**
 * Define hardcoded pets to avoid handling any non-ui operations.
 */
val pet1 = PetBean(
    id = "dc523f0ed25c",
    title = "A Little Thing about Android Module Paths",
    subtitle = "How to configure your module paths, instead of using Gradle’s default.",
    name = "Rivers",
    age = 2,
    sex = "母",
    userName = "HH ",
    putTime = "HH",
    like = "21",
    imagePetId = R.drawable.ic_launcher_background,
    imageUserId = R.drawable.ic_launcher_background
)

val pet2 = PetBean(
    id = "7446d8dfd7dc",
    title = "Dagger in Kotlin: Gotchas and Optimizations",
    subtitle = "Use Dagger in Kotlin! This article includes best practices to optimize your build time and gotchas you might encounter.",
    name = "Rivers",
    age = 2,
    sex = "母",
    userName = "HH ",
    putTime = "July 30",
    like = "11",
    imagePetId = R.drawable.ic_launcher_background,
    imageUserId = R.drawable.ic_launcher_background
)

val pet3 = PetBean(
    id = "ac552dcc1741",
    title = "From Java Programming Language to Kotlin — the idiomatic way",
    subtitle = "Learn how to get started converting Java Programming Language code to Kotlin, making it more idiomatic and avoid common pitfalls, by…",
    name = "Rivers",
    age = 2,
    sex = "母",
    userName = "HH ",
    putTime = "July 09",
    like = "11",
    imagePetId = R.drawable.ic_launcher_background,
    imageUserId = R.drawable.ic_launcher_background
)

val pet4 = PetBean(
    id = "84eb677660d9",
    title = "Locale changes and the AndroidViewModel antipattern",
    subtitle = "TL;DR: Expose resource IDs from ViewModels to avoid showing obsolete data.",
    name = "Rivers",
    age = 2,
    sex = "母",
    userName = "HH ",
    putTime = "April 03",
    like = "11",
    imagePetId = R.drawable.ic_launcher_background,
    imageUserId = R.drawable.ic_launcher_background
)

val pet5 = PetBean(
    id = "55db18283aca",
    title = "Collections and sequences in Kotlin",
    subtitle = "Working with collections is a common task and the Kotlin Standard Library offers many great utility functions. It also offers two ways of…",
    name = "Rivers",
    age = 2,
    sex = "母",
    userName = "HH ",
    putTime = "July 24",
    like = "11",
    imagePetId = R.mipmap.ic_dog,
    imageUserId = R.drawable.ic_launcher_background
)

val pets: List<PetBean> =
    listOf(
        pet1,
        pet2,
        pet3,
        pet4,
        pet5,
        pet1.copy(id = "pet6"),
        pet2.copy(id = "pet7"),
        pet3.copy(id = "pet8"),
        pet4.copy(id = "pet9"),
        pet5.copy(id = "pet10")
    )
