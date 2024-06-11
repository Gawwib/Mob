package com.example.sounds

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.sounds.entity.AppDatabase
import kotlinx.coroutines.launch

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen2()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen2() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent2 {
                scope.launch {
                    drawerState.close()
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color.White)
            ) {
                Header2 {
                    scope.launch {
                        drawerState.open()
                    }
                }
                LOGINP()
            }
        }
    )
}

@Composable
fun DrawerContent2(onCloseDrawer: () -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        TextButton(onClick = {
            context.startActivity(Intent(context, MainActivity::class.java))
            onCloseDrawer()
        }) {
            Text("Sign up")
        }
        TextButton(onClick = {
            context.startActivity(Intent(context, MainActivity2::class.java))
            onCloseDrawer()
        }) {
            Text("Log in")
        }
        TextButton(onClick = {
            context.startActivity(Intent(context, MainActivity3::class.java))
            onCloseDrawer()
        }) {
            Text("User")
        }
        TextButton(onClick = {
            context.startActivity(Intent(context, MainActivity4::class.java))
            onCloseDrawer()
        }) {
            Text("Shop")
        }
        TextButton(onClick = {
            context.startActivity(Intent(context, MainActivity5::class.java))
            onCloseDrawer()
        }) {
            Text("Home page")
        }
        TextButton(onClick = {
            context.startActivity(Intent(context, MainActivity6::class.java))
            onCloseDrawer()
        }) {
            Text("Dashboard")
        }
        TextButton(onClick = {
            context.startActivity(Intent(context, MainActivity7::class.java))
            onCloseDrawer()
        }) {
            Text("Wave Dynamics")
        }
    }
}
@Composable
fun Header2(onMenuClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF00008B))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onMenuClick,
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu Button",
                tint = Color.White
            )
        }
        Text(
            text = "Login",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LOGINP(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val userDao = remember { AppDatabase.getDatabase(context).userDao() }
    val scope = rememberCoroutineScope()
    var errorMessage by remember { mutableStateOf("") }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xffa7a7a7)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color(0xffd9d9d9), shape = RoundedCornerShape(16.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login",
                color = Color(0xff1b00c2),
                style = TextStyle(
                    fontSize = 36.sp,
                    shadow = Shadow(Color.Black.copy(alpha = 0.25f), offset = Offset(0f, 4f), blurRadius = 4f)
                ),
                modifier = Modifier.padding(vertical = 24.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.logosound),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(120.dp)
                    .padding(vertical = 24.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(24.dp))
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.height(24.dp))
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Button(
                onClick = {
                    scope.launch {
                        if (email.isNotEmpty() && password.isNotEmpty()) {

                            val user = userDao.getUserByEmail(email)
                            if (user != null && user.password == password) {
                                // Login successful, save email to SharedPreferences
                                val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                                with(sharedPreferences.edit()) {
                                    putString("logged_in_user_email", email)
                                    apply()
                                }

                                // Navigate to the next screen
                                context.startActivity(Intent(context, MainActivity5::class.java))
                            } else {
                                errorMessage = "Invalid email or password"
                            }
                        } else {
                            errorMessage = "Please enter email and password"
                        }
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff1b00c2)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Login",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MaterialTheme {
        MainScreen2()
    }
}