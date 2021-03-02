package com.example.androiddevchallenge.ui.home

import PetsRepository
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.pets.impl.BlockingFakePetsRepository
import com.example.androiddevchallenge.model.BaseBean
import com.example.androiddevchallenge.model.PetBean
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.compoment.VerticalGrid
import com.example.androiddevchallenge.ui.theme.Red300
import com.example.androiddevchallenge.ui.theme.Red700
import com.example.androiddevchallenge.ui.theme.Red800
import com.example.androiddevchallenge.ui.theme.Trans40
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun HomePage(
    navigateTo: (Screen) -> Unit,
    petsRepository: PetsRepository,
) {

    HomePage()

}

@Composable
fun HomePage() {

    /*Surface(
        color = Color.Red,
        modifier = Modifier
            //.fillMaxSize()
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(60.dp).copy(topStart = ZeroCornerSize, topEnd = ZeroCornerSize)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "哈哈哈")
        }
    }*/

    /*Scaffold(topBar = {
        val title = stringResource(id = R.string.app_name)
        TopAppBar(
            title = { Text(text = title) },
        )

    }) {


    }*/
    
    Surface() {
        val pets = loadFakePets()
        PetList(pets, {}, setOf(), {})
    }
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
    favorites: Set<String>,
    onToggleFavorite: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val petTop = pets[0]
    //val petsSimple = pets.subList(0, 2)
    val petsPopular = pets.subList(1, 10)
    //val petsHistory = pets.subList(7, 10)

    LazyColumn(modifier = modifier) {
        item { PetListTopSection(petTop, navigateTo) }
        //item { PetListView(petTop, navigateTo) }
        //item { PetListTopSection(petTop, navigateTo) }

        //item { PostListSimpleSection(petsSimple, navigateTo, favorites, onToggleFavorite) }
        item { PetListView(petsPopular, navigateTo) }
        //item { PostListHistorySection(petsHistory, navigateTo) }
    }
}

@Composable
private fun PetListTopSection(pet: PetBean, navigateTo: (Screen) -> Unit) {
    Text(
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp).fillMaxWidth(),
        text = "A pet waiting for you!",
        style = MaterialTheme.typography.h4,
        color = Red700,
        textAlign = TextAlign.Center
    )
    BannerView(
        pet = pet,
        modifier = Modifier.clickable(onClick = {
            //navigateTo(Screen.Article(pet.id))
        })
    )
    /*Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )*/
}

@Composable
fun BannerView(pet: PetBean, modifier: Modifier = Modifier) {
    // TUTORIAL CONTENT STARTS HERE
    val typography = MaterialTheme.typography
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        val imageModifier = Modifier
            .height(130.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
        Image(
            painter = painterResource(pet.imagePetId),
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(16.dp))
/*
        Text(
            text = pet.title,
            style = typography.h6
        )
        Text(
            text = pet.subtitle ?: "",
            style = typography.body2
        )*/
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
        color = Red800
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
        modifier = modifier.fillMaxWidth().height(200.dp)
    ) {
        Box(modifier = Modifier.clickable(onClick = {
            //navigateTo(Screen.Article(post.id))
        })) {

            Image(
                painter = painterResource(pet.imagePetId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(modifier = Modifier.background(color = Trans40).fillMaxWidth().height(40.dp),
            ) {
                Text(
                    text = pet.title,
                    style = MaterialTheme.typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    //modifier =
                )
                Text(
                    text = pet.userName,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}