package com.binar.listmovie.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.binar.listmovie.data.movie.GetAllMovieItem
import com.binar.listmovie.ui.theme.ListMovieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Detail : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListMovieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val getRecMovie =  intent?.getParcelableExtra<GetAllMovieItem>("recmovie")
                    val getPopularMovie =  intent?.getParcelableExtra<GetAllMovieItem>("popularmovie")
                    val getNewMovie =  intent?.getParcelableExtra<GetAllMovieItem>("newmovie")
                    if (getPopularMovie != null) {
                        DetailPopularMovie(getPopularMovie)
                    }
                    if (getRecMovie != null) {
                        DetailRecMovie(getRecMovie)
                    }
                    if (getNewMovie != null) {
                        DetailNewMovie(getNewMovie)
                    }

                }
            }
        }
    }
}

@Composable
fun DetailRecMovie(recMovie : GetAllMovieItem) {
    Column(modifier = Modifier.verticalScroll(
        rememberScrollState()
    )) {
        Text(text = "Detail", Modifier.padding(30.dp).align(Alignment.CenterHorizontally), fontSize = 24.sp)
        Row {

            Image(painter = rememberAsyncImagePainter(model = recMovie.image), contentDescription = "icon",
                Modifier
                    .padding(bottom = 20.dp).padding(start = 20.dp)
                    .size(240.dp))
            Column {
                Text(text = recMovie.title, Modifier.padding(10.dp), fontSize = 24.sp)
                Text(text = recMovie.releaseDate, Modifier.padding(10.dp), fontSize = 24.sp)
                Text(text = recMovie.director, Modifier.padding(10.dp), fontSize = 24.sp)
            }

        }

        Text(text = recMovie.synopsis, Modifier.padding(30.dp), fontSize = 20.sp)

    }
}

@Composable
fun DetailNewMovie(newMovie : GetAllMovieItem) {
    Column(modifier = Modifier.verticalScroll(
        rememberScrollState()
    )) {
        Text(text = "Detail", Modifier.padding(30.dp).align(Alignment.CenterHorizontally), fontSize = 24.sp)
        Row {

            Image(painter = rememberAsyncImagePainter(model = newMovie.image), contentDescription = "icon",
                Modifier
                    .padding(bottom = 20.dp).padding(start = 20.dp)
                    .size(240.dp))
            Column {
                Text(text = newMovie.title, Modifier.padding(10.dp), fontSize = 24.sp)
                Text(text = newMovie.releaseDate, Modifier.padding(10.dp), fontSize = 24.sp)
                Text(text = newMovie.director, Modifier.padding(10.dp), fontSize = 24.sp)
            }

        }

        Text(text = newMovie.synopsis, Modifier.padding(30.dp), fontSize = 20.sp)
    }
}

@Composable
fun DetailPopularMovie(popularMovie : GetAllMovieItem) {
    Column(modifier = Modifier.verticalScroll(
        rememberScrollState()
    )) {
        Text(text = "Detail", Modifier.padding(30.dp).align(Alignment.CenterHorizontally), fontSize = 24.sp)
        Row {

            Image(painter = rememberAsyncImagePainter(model = popularMovie.image), contentDescription = "icon",
                Modifier
                    .padding(bottom = 20.dp).padding(start = 20.dp)
                    .size(240.dp))
            Column {
                Text(text = popularMovie.title, Modifier.padding(10.dp), fontSize = 24.sp)
                Text(text = popularMovie.releaseDate, Modifier.padding(10.dp), fontSize = 24.sp)
                Text(text = popularMovie.director, Modifier.padding(10.dp), fontSize = 24.sp)
            }

        }

        Text(text = popularMovie.synopsis, Modifier.padding(30.dp), fontSize = 20.sp)
    }
}


