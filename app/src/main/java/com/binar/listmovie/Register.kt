package com.binar.listmovie

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binar.listmovie.ui.theme.ListMovieTheme

class Register : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListMovieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Register("Android")
                }
            }
        }
    }
}

@Composable
fun Register(name: String) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    var hasilusername by remember { mutableStateOf("") }
    var hasilpassword by remember { mutableStateOf("") }
    val mcontext = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Register", Modifier.padding(30.dp))
        Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "icon", Modifier.padding(bottom = 20.dp) )
        TextField(value = username, onValueChange = { username = it }, Modifier.padding(bottom = 10.dp),label = { Text("Masukan Username") } )
        TextField(value = email, onValueChange = { email = it }, Modifier.padding(bottom = 10.dp),label = { Text("Masukan Email") } )
        TextField(value = password, onValueChange = { password = it },label = { Text(" Masukan Password") })
        TextField(value = confirmpassword, onValueChange = { confirmpassword= it },label = { Text(" Konfirmasi Password") })
        Button(modifier = Modifier.padding(30.dp) ,onClick = {
            mcontext.startActivity(Intent(mcontext, Login::class.java))

        }){
            Text(text = "Register", )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ListMovieTheme {
        Register("Android")
    }
}