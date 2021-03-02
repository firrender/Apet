package com.example.androiddevchallenge.ui.detail

import PetsRepository
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.pets.impl.BlockingFakePetsRepository
import com.example.androiddevchallenge.model.BaseBean
import com.example.androiddevchallenge.model.PetBean
import com.example.androiddevchallenge.ui.theme.Black
import com.example.androiddevchallenge.ui.theme.White
import com.example.androiddevchallenge.ui.theme.primary
import kotlinx.coroutines.runBlocking

@Composable
fun DetailPage(
    petId: String,
    petsRepository: PetsRepository,
    onBack: () -> Unit
) {
    DetailPage(
        pet = loadFakePet(petId),
        onBack = onBack,
    )
}

@Composable
fun DetailPage(
    pet: PetBean,
    onBack: () -> Unit,
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    if (showDialog) {
        FunctionalityNotAvailablePopup { showDialog = false }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = pet.title,
                        color = White,
                        style = MaterialTheme.typography.h5,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 30.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            tint = White,
                            contentDescription = stringResource(R.string.navigate_back)
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = primary
            )
        },
        content = { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            PetContent(pet, modifier)
        },
        bottomBar = {
            BottomBar(
                pet = pet,
                onUnimplementedAction = { showDialog = true },
            )
        }
    )
}

@Composable
fun PetContent(pet: PetBean, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.padding(16.dp)
    ) {
        item {
            Spacer(Modifier.height(16.dp))
            Image(
                painter = painterResource(pet.imagePetId),
                contentDescription = null,
                modifier = Modifier
                    .heightIn(min = 180.dp)
                    .fillMaxWidth()
                    .clip(shape = MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(16.dp))
        }
        item {
            Text(text = pet.title, style = MaterialTheme.typography.h4)
            Spacer(Modifier.height(8.dp))
        }
        pet.detail.let { detail ->
            item {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = detail,
                        style = MaterialTheme.typography.body2,
                        lineHeight = 20.sp
                    )
                }
                Spacer(Modifier.height(16.dp))
            }
        }
        item {
            Spacer(Modifier.height(48.dp))
        }
    }
}

@Composable
private fun BottomBar(
    pet: PetBean,
    onUnimplementedAction: () -> Unit,
) {
    Surface(elevation = 4.dp) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = onUnimplementedAction,
                modifier = Modifier.width(100.dp).border(width = 1.dp, color = Black, shape = RoundedCornerShape(12.dp))
            ) {

                Text(
                    text = "ADOPT",
                    maxLines = 1,
                    color = Black,
                    style = MaterialTheme.typography.h5,
                )
            }
        }
    }
}

@Composable
private fun FunctionalityNotAvailablePopup(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            Text(
                text = "Give stray animals a home, adopt!\n \n \nAmong the rocks, there was a slightly flat SLATE, and that was their table.",
                style = MaterialTheme.typography.h6
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "OK",
                    style = MaterialTheme.typography.h5,
                )
            }
        },
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
private fun loadFakePet(petId: String): PetBean {
    val context = LocalContext.current
    val pet = runBlocking {
        (BlockingFakePetsRepository(context).getPets(petId) as BaseBean.Success).data
    }
    return pet
}