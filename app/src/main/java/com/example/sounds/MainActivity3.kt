package com.example.sounds

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.sounds.entity.AppDatabase
import com.example.sounds.entity.User
import kotlinx.coroutines.launch

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen3()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen3() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // State to hold the user data
    var user by remember { mutableStateOf<User?>(null) }

    // Fetch the user data asynchronously
    LaunchedEffect(Unit) {
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("logged_in_user_email", null)
        if (email != null) {
            val db = AppDatabase.getDatabase(context)
            user = db.userDao().getUserByEmail(email)
        }
    }

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
            MYPROFILEP(user) {
                scope.launch {
                    drawerState.open()
                }
            }
        }
    )
}

@Composable
fun MYPROFILEP(user: User?, onMenuClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xffa7a7a7))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(87.dp)
                .background(color = Color(0xff1b00c2)),
            contentAlignment = Alignment.Center
        ) {
            Row(
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
                    text = "My profile",
                    color = Color.White,
                    style = TextStyle(fontSize = 20.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .size(139.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xffd9d9d9)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.androidlogo),
                        contentDescription = "Profile Picture",
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = user?.fullName ?: "User123",
                    color = Color(0xff262422),
                    textAlign = TextAlign.Center,
                    lineHeight = 6.em,
                    style = TextStyle(fontSize = 20.sp)
                )
            }

            item {
                ProfileItem(label = "Your Email", value = user?.email ?: "xxxxxxmail.com")
            }

            item {
                ProfileItem(label = "Phone Number", value = user?.phone ?: "+93123135")
            }

            item {
                ProfileItem(label = "Company", value = "www.SoundS.com")
            }

            item {
                ProfileItem(label = "Password", value = "********")
            }
        }
    }
}

@Composable
fun ProfileItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            color = Color(0xff262422),
            lineHeight = 8.57.em,
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(12.dp))
                .border(border = BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 15.dp)
        ) {
            Text(
                text = value,
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 8.57.em,
                style = TextStyle(fontSize = 14.sp)
            )
        }
    }
}
