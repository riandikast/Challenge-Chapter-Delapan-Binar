package com.binar.listmovie.view

import com.binar.listmovie.datastore.UserManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
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
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListMovieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Login("Android")
                }
            }
        }
    }
}

@Composable
fun Login(name: String) {
    val mcontext = LocalContext.current
    val userViewModel = viewModel(modelClass = ViewModelUser::class.java)
    val dataUser by userViewModel.dataUserState.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val userManager = UserManager(mcontext)
    var salah: Boolean
    salah = false
    var passwordVisible by rememberSaveable { mutableStateOf(false) }


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Login", Modifier.padding(30.dp))
        Image(
            painter = painterResource(R.drawable.icon),
            contentDescription = "icon",
            Modifier
                .padding(bottom = 20.dp)
                .size(120.dp)
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            Modifier.padding(10.dp),
            label = { Text("Email") },
            placeholder = { Text("Email") },)
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

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            }
        )
        Button(
            modifier = Modifier.padding(30.dp),
            colors = ButtonDefaults.buttonColors(Orange),


            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    for (i in dataUser.indices) {
                        if (email == dataUser[i].email && password == dataUser[i].password) {
                            scope.launch {
                                userManager.saveDataUser(
                                    dataUser[i].id,
                                    dataUser[i].email,
                                    dataUser[i].password,
                                    dataUser[i].username,
                                    dataUser[i].completeName,
                                    dataUser[i].dateofbirth,
                                    dataUser[i].address,
                                    dataUser[i].image
                                )
                                userManager.saveDataLogin("true")
                            }

                            salah = false
                            mcontext.startActivity(Intent(mcontext, Home::class.java))
                            break
                        } else {
                            salah = true
                        }

                    }
                    if (salah) {
                        android.widget.Toast.makeText(
                            mcontext,
                            "Username atau Password Salah",
                            android.widget.Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    android.widget.Toast.makeText(
                        mcontext,
                        "Harap Isi Semua Data",
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                }


            }) {
            Text(text = "Login")
        }
        Row {

            Text(text = "Belum Punya Akun? ")
            val text = stringResource(R.string.daftar)
            ClickableText(
                text = AnnotatedString(text),
                onClick = {
                    mcontext.startActivity(Intent(mcontext, Register::class.java))
                }
            )

        }
    }
}

