package com.example.buzzz.ui.theme

// PhotoListScreen.kt

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.buzzz.R

data class Person(val id: Int, val name: String, val photoRes: Int)

val peopleList = listOf(
    Person(1, "John Doe", R.drawable.splash1),
    Person(2, "Jack Ryan", R.drawable.splash5),
    // Add more people as needed
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color.Blue) // Set the desired background color here
    ) {
        CenterAlignedTopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

//                    Image(
//                    modifier = Modifier
//                        .size(dimensionResource(id = R.dimen.image_size))
//                        .padding(dimensionResource(id = R.dimen.padding_small)),
//                    painter = painterResource(R.drawable.ic_woof_logo),
//
//                    contentDescription = null
//                )
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.displaySmall,
                        color = Color.Black // Set the text color if needed
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth() // Ensure the TopAppBar fills the width of the screen
                .statusBarsPadding() // Apply status bar padding
                .navigationBarsPadding().background(MaterialTheme.colorScheme.primary) // Apply navigation bar padding
        )
    }
}


@Composable
fun PhotoListScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar()
        }
    ) {
        LazyColumn (contentPadding = it){
            items(peopleList) { person ->
                PhotoListItem(person = person)
//                Divider()
            }
        }
    }
}

@Composable
fun PhotoListItem(person: Person) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
//        elevation = 4.dp, // Adjust the elevation as needed
        shape = RoundedCornerShape(16.dp) // Set the desired corner radius
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Photo
            Image(
                painter = painterResource(id = person.photoRes),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .scale(1.2f),
                contentScale = ContentScale.Crop
            )

            // Spacer
            Spacer(modifier = Modifier.width(16.dp))

            // Name
            Text(
                text = person.name,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
fun PhotoListScreenPreview() {
    val navController = rememberNavController()
    PhotoListScreen(navController)
}
