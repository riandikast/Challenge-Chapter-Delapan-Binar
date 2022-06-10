@file:Suppress("CanBeVal")

package com.binar.listmovie.view

import com.binar.listmovie.datastore.UserManager
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.binar.listmovie.data.movie.GetAllMovieItem
import com.binar.listmovie.ui.theme.ListMovieTheme
import com.binar.listmovie.viewmodel.ViewModelMovie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
                    val viewModelMovie: ViewModelMovie =
                        viewModel(modelClass = ViewModelMovie::class.java)
                    val dataPopularMovie by viewModelMovie.dataPopularMovieState.collectAsState()
                    val dataNewMovie by viewModelMovie.dataNewMovieState.collectAsState()
                    val dataRecMovie by viewModelMovie.dataRecMovieState.collectAsState()

                    Column {
                        Box(modifier = Modifier.padding(bottom = 30.dp)) {
                                Username("Username")
                                Title("Home")
                                Logout("Logout")
                        }

                        Column(modifier = Modifier
                            .verticalScroll(rememberScrollState())) {
                            Text(text = "New Movie",
                                Modifier
                                    .padding(start = 30.dp)
                                    .padding(top = 20.dp), fontSize = 19.sp)

                            LazyRow {
                                if (dataNewMovie.isEmpty()) {
                                    item {
                                    }
                                }

                                items(dataNewMovie) {
                                    AdapterNewMovie(newMovie  = it)
                                }
                            }

                            Text(text = "Popular Movie",
                                Modifier
                                    .padding(start = 30.dp)
                                    .padding(top = 40.dp), fontSize = 19.sp)

                            LazyRow {
                                if (dataPopularMovie.isEmpty()) {
                                    item {
                                    }
                                }

                                items(dataPopularMovie) {
                                    AdapterPopularMovie(popularMovie  = it)
                                }
                            }
                            Text(text = "Recommended For You",
                                Modifier
                                    .padding(start = 30.dp)
                                    .padding(top = 40.dp), fontSize = 19.sp)

                            LazyRow {
                                if (dataRecMovie.isEmpty()) {
                                    item {
                                    }
                                }

                                items(dataRecMovie) {
                                    AdapterRecMovie(recMovie  = it)
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun Username(name: String) {
    val mcontext = LocalContext.current
    val userManager = UserManager(mcontext)
    val username = userManager.userUsername.collectAsState(initial = "")
    val showUsername = username.value
    Row {
        Text(text = "Hello $showUsername",
            Modifier
                .padding(top = 30.dp)
                .padding(start = 30.dp), fontSize = 20.sp)

    }



}


@Composable
fun Title(name: String) {
    Row(modifier = Modifier.fillMaxWidth(),  horizontalArrangement = Arrangement.Center) {
        Text(text = "Home",
            Modifier
                .padding(top = 30.dp)
                , fontSize = 20.sp)
    }
}


@Composable
fun Logout(name: String) {
    var open = remember { mutableStateOf(false)  }
    val mcontext = LocalContext.current
    val userManager = UserManager(mcontext)
    val scope = rememberCoroutineScope()
    if (open.value){
        SimpleAlertDialog(show = true, onDismiss = {open.value = false}) {
            scope.launch {
                userManager.deleteDataLogin()
            }
            mcontext.startActivity(Intent(mcontext, Login::class.java))
        }

    }
    Row(modifier = Modifier.fillMaxWidth(),  horizontalArrangement = Arrangement.End) {
        Text(text = "Logout",
            Modifier
                .padding(top = 30.dp)
                .padding(end = 30.dp)
                .clickable {
                    open.value = true
                }
            , fontSize = 20.sp)
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdapterPopularMovie(popularMovie: GetAllMovieItem) {
    val mcontext = LocalContext.current
    Column(modifier = Modifier.padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally) {


        Card(shape = RoundedCornerShape(10.dp), modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .width(200.dp), onClick = {
            val intent = Intent(mcontext, Detail::class.java)
            intent.putExtra("popularmovie", popularMovie)
            mcontext.startActivity(intent)

        }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = popularMovie.image), contentDescription = "icon",
                    Modifier
                        .padding(bottom = 20.dp)
                        .size(200.dp)
                )
                Text(text = popularMovie.title, color = Color.Black, fontWeight = FontWeight.Normal, modifier = Modifier.padding(20.dp))


            }
        }
    }
}

@SuppressLint("ComposableNaming")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdapterNewMovie(newMovie: GetAllMovieItem) {
    val mcontext = LocalContext.current
    Column(modifier = Modifier.padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally) {

        Card(shape = RoundedCornerShape(10.dp), modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .width(200.dp), onClick = {
            val intent = Intent(mcontext, Detail::class.java)
            intent.putExtra("newmovie", newMovie)
            mcontext.startActivity(intent)

        }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = newMovie.image), contentDescription = "icon",
                    Modifier
                        .padding(bottom = 20.dp)
                        .size(200.dp)
                )
                Text(text = newMovie.title, color = Color.Black, fontWeight = FontWeight.Normal, modifier = Modifier.padding(20.dp))


            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdapterRecMovie(recMovie: GetAllMovieItem) {
    val mcontext = LocalContext.current
    Column(modifier = Modifier.padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally) {

        Card(shape = RoundedCornerShape(10.dp), modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .width(200.dp), onClick = {
            val intent = Intent(mcontext, Detail::class.java)
            intent.putExtra("recmovie", recMovie)
            mcontext.startActivity(intent)
        }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = recMovie.image), contentDescription = "icon",
                    Modifier
                        .padding(bottom = 20.dp)
                        .size(200.dp)
                )
                Text(text = recMovie.title, color = Color.Black, fontWeight = FontWeight.Normal, modifier = Modifier.padding(20.dp))


            }
        }
    }
}
@Composable
fun SimpleAlertDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = onConfirm,
            confirmButton = {
                TextButton(onClick = onConfirm)
                { Text(text = "OK", fontSize = 20.sp) }
            },
            dismissButton = {
                TextButton(onClick = onDismiss)
                { Text(text = "Cancel", fontSize = 20.sp) }
            },
            title = { Text(text = "Konfirmasi", fontSize = 20.sp) },
            text = { Text(text = "Apakah Kamu Yakin Ingin Logout?", fontSize = 20.sp) }
        )
    }}



