package com.binar.listmovie

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binar.listmovie.ui.theme.ListMovieTheme

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
                    Details("Android")
                }
            }
        }
    }
}

@Composable
fun Details(name: String) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Detail", Modifier.padding(30.dp))
        Text(text = "Judul", Modifier.padding(10.dp))
        Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "icon", Modifier.padding(bottom = 20.dp) )
        Text(text = "Deskripsi", Modifier.padding(10.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    ListMovieTheme {
        Details("Android")
    }
}