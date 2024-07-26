package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
/*
* Method to get the artwork title
* */
fun getArtworkTitle(artworkIndex: Int): Int {
    return when(artworkIndex){
        0 -> R.string.artwork_title_1
        1 -> R.string.artwork_title_2
        2 -> R.string.artwork_title_3
        3 -> R.string.artwork_title_4
        4 -> R.string.artwork_title_5
        5 -> R.string.artwork_title_6
        else -> R.string.artwork_title_1
    }
}
/*
* Method to get the artist name and year
* */
fun getArtworkArtistNameYear(index: Int): Int {
    return when(index){
        0 -> R.string.artist_name_year_1
        1 -> R.string.artist_name_year_2
        2 -> R.string.artist_name_year_3
        3 -> R.string.artist_name_year_4
        4 -> R.string.artist_name_year_5
        5 -> R.string.artist_name_year_6
        else -> R.string.artist_name_year_1
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    val images = listOf(
        R.drawable.artwork_image_1,
        R.drawable.artwork_image_2,
        R.drawable.artwork_image_3,
        R.drawable.artwork_image_4,
        R.drawable.artwork_image_5,
        R.drawable.artwork_image_6
    )
    var currentIndex by remember { mutableStateOf(0) }
    ArtSpaceWithImageAndText(
        textTitleId = getArtworkTitle(currentIndex),
        textArtistNameYearId = getArtworkArtistNameYear(currentIndex),
        imageId = images[currentIndex],
        onNextButtonClicked = {
                              if (currentIndex < images.size - 1) {
                                  currentIndex++
                              } else {
                                  currentIndex = 0
                              }
        },
        onPreviousButtonClicked = {
            if (currentIndex > 0) {
                currentIndex--
            } else {
                currentIndex = images.size - 1
            }
        })


}

@Composable
fun ArtSpaceWithImageAndText(
    modifier: Modifier = Modifier,
    textTitleId:Int,
    textArtistNameYearId:Int,
    imageId:Int,
    onNextButtonClicked: () -> Unit,
    onPreviousButtonClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Surface(
            modifier = Modifier
                .size(height = 400.dp, width = 350.dp)
                .padding(10.dp),
            shadowElevation = 12.dp,
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier,
                contentScale = ContentScale.Fit

            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = textTitleId),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            Text(text = stringResource(id = textArtistNameYearId))
        }
        Row {
            Button(
                onClick = onPreviousButtonClicked,
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
            )
            {
                Text(
                    text = "Previous",
                    fontSize = 17.sp
                )
            }
            Button(
                onClick = onNextButtonClicked,
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
            )
            {
                Text(
                    text = "Next",
                    fontSize = 17.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceAppTheme {
        ArtSpaceApp()
    }
}