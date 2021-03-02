package com.example.androiddevchallenge.data.pets

import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.PetBean

val pet1 = PetBean(
    id = "01",
    title = "Let them no longer stray",
    detail = "Just as people said before, looking at these lives, because we can survive, more proud of a thing.And the warmth and kindness of the city, is it not because of them, diverging the light.Hope, in the eyes of animals, human beings will always be the spirit that can protect them for several generations.",
    name = "Rivers",
    age = 2,
    sex = "母",
    userName = "HH ",
    putTime = "HH",
    like = "21",
    imagePetId = R.mipmap.ic_banner,
    imageUserId = R.drawable.ic_launcher_background
)

val pet2 = PetBean(
    id = "02",
    title = "I picked up a stray dog and wanted to adopt it",
    detail = "\"Because of us, these lives are alive.\" Vines on either side of a low, simple glass door shoot out into the sun.Push the door, two tall and dense plantain trees in the courtyard, grow particularly eye-catching.",
    name = "Rivers",
    age = 2,
    sex = "母",
    userName = "HH ",
    putTime = "July 30",
    like = "11",
    imagePetId = R.mipmap.ic_dog,
    imageUserId = R.drawable.ic_launcher_background
)

val pet3 = PetBean(
    id = "03",
    title = "Stop taking stray dogs to rescue stations",
    detail = "The arrival of the stranger, set off a burst of dog barking climax in the hospital, the moment was surrounded by a variety of big and small, breeds of different dogs, Cheng Cheng nervous and afraid, palms straight sweating.Unlike the stray cats and dogs we see on the road, \"they are harmless, very gentle and nice\", but I was given a tranquilizer.Slowly you will find that all the animals here have no edge in their eyes.Obviously, they have made this place their home, every stranger who came here, as a friend.",
    name = "Rivers",
    age = 2,
    sex = "母",
    userName = "HH ",
    putTime = "July 09",
    like = "11",
    imagePetId = R.mipmap.ic_dog1,
    imageUserId = R.drawable.ic_launcher_background
)

val pet4 = PetBean(
    id = "04",
    title = "Please help him",
    detail = "Many stray dogs are afraid of people. My father's unit adopted stray dogs. At the beginning, they were also afraid of people, but later they got used to them and they could not.Stray dogs are so used to being outside that they don't want to wear a collar. Besides, I believe that over time, you will build up a relationship and she won't get lost",
    name = "Rivers",
    age = 2,
    sex = "母",
    userName = "HH ",
    putTime = "April 03",
    like = "11",
    imagePetId = R.mipmap.ic_dog2,
    imageUserId = R.drawable.ic_launcher_background
)

val pet5 = PetBean(
    id = "05",
    title = "Its name is Little Flower",
    detail = "Stray dogs have a short life span. They usually die of heat in summer and freeze to death in winter. They survive winter and summer, and are killed by infectious diseases and parasites in spring and autumn.Few stray dogs survive more than a year.There is no room for stray dogs in the city because there is neither food nor water to drink.The garbage is all in the trash can, the trash can is generally very high, the dog is difficult to reach, can only pick up a scattered outside.If there is no rain in the city for a long time, it is difficult to find water. You can observe it nearby. It is difficult to find a place with water storage.Therefore, what dogs become stray dogs can not survive.Then why are there so many stray dogs on the streets?Because people keep abandoning dogs and replacing them with new ones.You have to be careful with a dog, either not at all or all at once.Can not be sure of the best not to raise, rub a rub against the dog.I also do not recommend people to feed the stray dog, if you can not take it home, let it die may be the best relief for the stray dog.And stray dogs are a potential danger to people, especially children.",
    name = "Rivers",
    age = 2,
    sex = "母",
    userName = "HH ",
    putTime = "July 24",
    like = "11",
    imagePetId = R.mipmap.ic_dog3,
    imageUserId = R.drawable.ic_launcher_background
)

val pets: List<PetBean> =
    listOf(
        pet1,
        pet2,
        pet3,
        pet4,
        pet5,
        pet2.copy(id = "pet7"),
        pet3.copy(id = "pet8"),
        pet4.copy(id = "pet9"),
        pet5.copy(id = "pet10"),
    )
