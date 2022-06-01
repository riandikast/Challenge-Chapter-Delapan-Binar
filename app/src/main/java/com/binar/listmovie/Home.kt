package com.binar.listmovie

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binar.listmovie.ui.theme.ListMovieTheme

class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListMovieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val repo = MovieRepository()
                    val data = repo.getalldata()
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        judul( "Home")
                        LazyColumn(){
                            items(items = data){
                                    movie -> getmovie(movie = movie)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun judul(name:String){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Home", Modifier.padding(30.dp))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun getmovie(movie: Movie) {
    val mcontext = LocalContext.current
    Column(modifier = Modifier.padding(15.dp)) {
        Card(shape = RoundedCornerShape(10.dp), modifier = Modifier
            .fillMaxWidth()
            .height(80.dp), onClick = {
            mcontext.startActivity(Intent(mcontext, Detail::class.java))
        }){
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)) {
                Text(text = "Judul : ${movie.judul}", color = Color.Black, fontWeight = FontWeight.Normal, modifier = Modifier.padding(top = 15.dp) )
                Text(text = "Deskripsi : ${movie.deskripsi}")

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ListMovieTheme {
        val repo = MovieRepository()
        val data = repo.getalldata()

        LazyColumn(){

            items(items = data){
                    movie -> getmovie(movie = movie)
            }
        }
    }
}