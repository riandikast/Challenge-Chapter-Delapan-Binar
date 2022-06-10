package com.binar.listmovie.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.binar.listmovie.R
import com.binar.listmovie.ui.theme.ListMovieTheme
import com.binar.listmovie.ui.theme.Orange
import com.binar.listmovie.viewmodel.ViewModelUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
    val mcontext = LocalContext.current
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    val userViewModel = viewModel(modelClass = ViewModelUser::class.java)
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Register", Modifier.padding(30.dp))
        Image(
            painter = painterResource(R.drawable.icon),
            contentDescription = "icon",
            Modifier
                .padding(bottom = 20.dp)
                .size(120.dp)
        )
        TextField(
            value = username,
            onValueChange = { username = it },
            Modifier.padding(bottom = 10.dp),
            label = { Text("Username") })
        TextField(
            value = email,
            onValueChange = { email = it },
            Modifier.padding(bottom = 10.dp),
            label = { Text("Email") })
        TextField(
            value = password,
            onValueChange = { password = it },
            Modifier.padding(bottom = 10.dp),
            label = { Text("Password") },
            singleLine = true,
            placeholder = { Text("Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector  = image, description)
                }
            }
        )
        TextField(
            value = confirmpassword,
            onValueChange = { confirmpassword = it },
            Modifier.padding(bottom = 10.dp),
            label = { Text("Konfirmasi Password") },
            singleLine = true,
            placeholder = { Text("Konfirmasi Password") },
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (confirmPasswordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (confirmPasswordVisible) "Hide password" else "Show password"

                IconButton(onClick = {confirmPasswordVisible = !confirmPasswordVisible}){
                    Icon(imageVector  = image, description)
                }
            }
        )
        Button(
            modifier = Modifier.padding(30.dp),
            colors = ButtonDefaults.buttonColors(Orange),
            onClick = {
                if (username.isNotEmpty() && email.isNotEmpty()
                    && password.isNotEmpty()
                    && confirmpassword.isNotEmpty()
                ) {
                    if (password == confirmpassword) {
                        userViewModel.registerLiveData(username, email, password)
                        android.widget.Toast.makeText(
                            mcontext,
                            "Register Berhasil",
                            android.widget.Toast.LENGTH_SHORT
                        ).show()
                        mcontext.startActivity(Intent(mcontext, Login::class.java))
                    }else{
                        android.widget.Toast.makeText(
                            mcontext,
                            "Konfirmasi Password Tidak Sesuai",
                            android.widget.Toast.LENGTH_SHORT
                        ).show()
                    }

                }else{
                    android.widget.Toast.makeText(
                        mcontext,
                        "Harap Isi Semua Data",
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                }

            }) {
            Text(text = "Register")
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview4() {
//    ListMovieTheme {
//        Register("Android")
//    }
//}