package com.example.sounds

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.launch

class MainActivity6 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent {
                scope.launch {
                    drawerState.close()
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Header {
                    scope.launch {
                        drawerState.open()
                    }
                }
                Body()
            }
        }
    )
}

@Composable
fun DrawerContent(onCloseDrawer: () -> Unit) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Menu",
            modifier = Modifier
                .padding(16.dp)
                .background(Color(0xFF00008B))
                .fillMaxWidth(),
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

@Composable
fun Header(onMenuClick: () -> Unit) {
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
            text = "Dashboard",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Body() {
    var showDialog by remember { mutableStateOf(false) }
    var showAddDialog by remember { mutableStateOf(false) }
    var newDashboardName by remember { mutableStateOf("") }
    val dashboards = remember { mutableStateListOf("Spa Center") }
    val images = mapOf(
        "Spa Center" to R.drawable.dashboard
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            dashboards.forEach { label ->
                DashboardRow(label = label, onClick = {
                    showDialog = true
                }, onDelete = {
                    dashboards.remove(label)
                })
            }
        }

        if (showDialog) {
            ImageDialog(onDismiss = { showDialog = false })
        }

        FloatingActionButton(
            onClick = { showAddDialog = true },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            shape = RoundedCornerShape(50),
            containerColor = Color(0xFF00008B),
            contentColor = Color.White
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Dashboard")
        }

        if (showAddDialog) {
            NewDashboardDialog(
                newDashboardName = newDashboardName,
                onNameChange = { newDashboardName = it },
                onAdd = {
                    dashboards.add(newDashboardName)
                    newDashboardName = ""
                    showAddDialog = false
                },
                onDismiss = { showAddDialog = false }
            )
        }
    }
}

@Composable
fun DashboardRow(label: String, onClick: () -> Unit, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f)
        )
        Row {
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Delete Dashboard")
            }
            IconButton(onClick = onClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Open Dashboard")
            }
        }
    }
}

@Composable
fun ImageDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.dashboard),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
            Button(
                onClick = onDismiss,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                Text("Close")
            }
        }
    }
}

@Composable
fun NewDashboardDialog(
    newDashboardName: String,
    onNameChange: (String) -> Unit,
    onAdd: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Create New Dashboard", fontSize = 20.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = newDashboardName,
                    onValueChange = onNameChange,
                    label = { Text("Dashboard Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = onAdd) {
                        Text("Add")
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 393, heightDp = 852)
@Composable
private fun HomepagePreview() {
    MyApp()
}
