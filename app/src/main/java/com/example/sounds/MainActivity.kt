package com.example.sounds

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.sounds.entity.AppDatabase
import com.example.sounds.entity.User
import com.example.sounds.theme.SoundSTheme


import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoundSTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val isUserSignedIn = remember { mutableStateOf(false) }

    // This is a placeholder check. Replace this with your actual sign-in check logic
    if (isUserSignedIn.value) {
        LaunchedEffect(Unit) {
            context.startActivity(Intent(context, MainActivity2::class.java))
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent1 {
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
                Headerr {
                    scope.launch {
                        drawerState.open()
                    }
                }
                Bodyy()
            }
        }
    )
}

@Composable
fun DrawerContent1(onCloseDrawer: () -> Unit) {
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
fun Headerr(onMenuClick: () -> Unit) {
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
            text = "Main Activity",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Bodyy() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { SIGNUPP() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SIGNUPP(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val database = remember { AppDatabase.getDatabase(context) }
    val userDao = remember { database.userDao() }

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    fun isPasswordValid(password: String): Boolean {
        val hasLetter = password.any { it.isLetter() }
        val hasUniqueOrDigit = password.any { it.isDigit() || it in "!@#$%^&*()-_+=<>?/" }
        return password.length >= 8 && hasLetter && hasUniqueOrDigit
    }

    fun areFieldsFilled(): Boolean {
        return fullName.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
    }
    fun isEmailValid(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$".toRegex()
        return email.matches(emailRegex)
    }
    fun isPhoneValid(phone: String): Boolean {
        return phone.all { it.isDigit() }
    }

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
                text = "Sign up",
                color = Color(0xff1b00c2),
                style = TextStyle(
                    fontSize = 36.sp,
                    shadow = Shadow(Color.Black.copy(alpha = 0.25f), offset = Offset(0f, 4f), blurRadius = 4f)
                ),
                modifier = Modifier.padding(vertical = 24.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.logosound), // Adjust this to your logo resource
                contentDescription = "Logo",
                modifier = Modifier
                    .size(120.dp)
                    .padding(vertical = 24.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Name") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    when {
                        !areFieldsFilled() -> {
                            errorMessage = "All fields must be filled"
                        }
                        !isPasswordValid(password) -> {
                            errorMessage = "Password must be at least 8 characters long and contain a unique character"
                        }
                        !isEmailValid(email) -> {
                            errorMessage = "Email is not in the correct format (name@example.com)"
                        }
                        !isPhoneValid(phone) -> {
                            errorMessage = "Phone number must contain only digits"
                        }
                        password != confirmPassword -> {
                            errorMessage = "Passwords do not match"
                        }
                        else -> {
                            errorMessage = ""
                            val newUser = User(
                                fullName = fullName,
                                email = email,
                                phone = phone,
                                password = password
                            )
                            scope.launch {
                                userDao.insert(newUser)
                                context.startActivity(Intent(context, MainActivity2::class.java))
                            }
                            // Optionally, show a success message or navigate away
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
                    text = "SUBMIT",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            TextButton(
                onClick = {
                    context.startActivity(Intent(context, MainActivity2::class.java))
                }
            ) {
                Text("Already signed in? Log in")
            }
        }
    }
}

@Preview(widthDp = 393, heightDp = 852)
@Composable
private fun SIGNUPPPreview() {
    SoundSTheme {
        MainScreen()
    }
}