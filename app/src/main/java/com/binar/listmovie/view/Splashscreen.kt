package com.binar.listmovie.view

import com.binar.listmovie.datastore.UserManager
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.binar.listmovie.R
import com.binar.listmovie.ui.theme.ListMovieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListMovieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Greeting("Android")
                }
            }
            val mcontext = LocalContext.current
            val userManager = UserManager(mcontext)
            val isLogin = userManager.userLogin.collectAsState(initial = "")
            val loginStatus = isLogin.value
            if (loginStatus == "true"){
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, Home::class.java))
                    finish()
                }, 2000)
            }else if ((loginStatus == "false")){
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, Login::class.java))
                    finish()
                }, 2000)
            }

        }
    }
}

@Composable
fun Greeting(name: String) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.b), contentDescription = "icon")

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ListMovieTheme {
        Greeting("Android")
    }
}