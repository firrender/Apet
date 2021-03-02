package com.example.androiddevchallenge.ui.home

import com.example.androiddevchallenge.data.pets.PetsRepository
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.pets.impl.BlockingFakePetsRepository
import com.example.androiddevchallenge.model.BaseBean
import com.example.androiddevchallenge.model.PetBean
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.ThemedPreview
import com.example.androiddevchallenge.ui.compoment.VerticalGrid
import com.example.androiddevchallenge.ui.theme.White
import com.example.androiddevchallenge.ui.theme.primary
import kotlinx.coroutines.runBlocking

@Composable
fun HomePage(
    navigateTo: (Screen) -> Unit,
    petsRepository: PetsRepository,
) {
    HomePage(
        pets = loadFakePets(),
        navigateTo = navigateTo,
    )
}

@Composable
fun HomePage(
    pets: List<PetBean>,
    navigateTo: (Screen) -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "A pet waiting for you!",
                    color = White,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                ) },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = primary
        )
    },content = {
        Surface() {
            PetList(pets, navigateTo)
        }
    })
}

@Composable
private fun loadFakePets(): List<PetBean> {
    val context = LocalContext.current
    val pets = runBlocking {
        BlockingFakePetsRepository(context).getPets()
    }
    return (pets as BaseBean.Success).data
}

@Composable
private fun PetList(
    pets: List<PetBean>,
    navigateTo: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    val petTop = pets[0]
    val petsPopular = pets.subList(1, 9)

    LazyColumn(modifier = modifier) {
        item { BannerSection(petTop, navigateTo) }
        item { PetListView(petsPopular, navigateTo) }
    }
}

@Composable
private fun BannerSection(pet: PetBean, navigateTo: (Screen) -> Unit) {
    BannerView(
        pet = pet,
        modifier = Modifier.clickable(onClick = {
            navigateTo(Screen.Detail(pet.id))
        })
    )
    /*Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )*/
}

@Composable
fun BannerView(pet: PetBean, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        val imageModifier = Modifier
            .height(130.dp)
            .fillMaxWidth()
            //.clip(shape = MaterialTheme.shapes.medium)
            .clip(shape = RoundedCornerShape(16.dp).copy(topStart = ZeroCornerSize, topEnd = ZeroCornerSize))
        Image(
            painter = painterResource(pet.imagePetId),
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )
        Column {
            Text(
                text = pet.title,
                modifier = Modifier.width(200.dp).padding(10.dp),
                textAlign = TextAlign.Start,
                color = White,
                style = MaterialTheme.typography.h5,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = pet.detail,
                modifier = Modifier.width(300.dp).padding(10.dp),
                textAlign = TextAlign.Start,
                color = White,
                style = MaterialTheme.typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun PetListView(
    pets: List<PetBean>,
    navigateTo: (Screen) -> Unit
) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = "Popular Recommend",
        style = MaterialTheme.typography.h5,
        color = primary
    )
    VerticalGrid(modifier = Modifier.padding(horizontal = 16.dp)) {
        pets.forEach { pet -> PetCardItem(pet, navigateTo, Modifier.padding(10.dp)) }
    }
}

@Composable
fun PetCardItem(
    pet: PetBean,
    navigateTo: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {

        Column(modifier = Modifier.clickable(
            onClick = {
                navigateTo(Screen.Detail(pet.id))
            }
        )) {

            Image(
                painter = painterResource(pet.imagePetId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(120.dp).fillMaxWidth()
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = pet.title,
                    style = MaterialTheme.typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = pet.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview("Home page body")
@Composable
fun PreviewHomeScreenBody() {
    ThemedPreview {
        val pets = loadFakePets()
        PetList(pets, { })
    }
}