package com.example.sounds

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class MainActivity5 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HOMEPAGEP()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HOMEPAGEP(modifier: Modifier = Modifier) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent5 {
                scope.launch {
                    drawerState.close()
                }
            }
        },
        content = {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = Color(0xffa7a7a7))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top

            ) {

                IconButton(
                    onClick = { scope.launch { drawerState.open() } },
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Start)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu Button",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.logosound),
                    contentDescription = "Main Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.boselogo),
                        contentDescription = "Image 1",
                        modifier = Modifier
                            .size(100.dp),
                    )
                    Image(
                        painter = painterResource(id = R.drawable.electrovoicelogo),
                        contentDescription = "Image 2",
                        modifier = Modifier
                            .size(100.dp),
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.jbllogo),
                        contentDescription = "Image 3",
                        modifier = Modifier
                            .size(100.dp),
                    )
                    Image(
                        painter = painterResource(id = R.drawable.rcflogo),
                        contentDescription = "Image 4",
                        modifier = Modifier
                            .size(100.dp),
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sennheiserlogo),
                        contentDescription = "Image 5",
                        modifier = Modifier
                            .size(100.dp),
                    )
                    Image(
                        painter = painterResource(id = R.drawable.shurelogo),
                        contentDescription = "Image 6",
                        modifier = Modifier
                            .size(100.dp),
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Add new image at the bottom
                Image(
                    painter = painterResource(id = R.drawable.audaclogo),
                    contentDescription = "New Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                )
            }
        }
    )
}

@Composable
fun DrawerContent5(onCloseDrawer: () -> Unit) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Menu",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .padding(16.dp),
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
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

@Preview(widthDp = 393, heightDp = 852)
@Composable
private fun HOMEPAGEPPreview() {
    HOMEPAGEP()
}