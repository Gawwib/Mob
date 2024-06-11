package com.example.sounds

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.launch

class MainActivity7 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp7()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp7() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent7 {
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
                Header7 {
                    scope.launch {
                        drawerState.open()
                    }
                }
                BodyContent()
            }
        }
    )
}

@Composable
fun DrawerContent7(onCloseDrawer: () -> Unit) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Menu",
            modifier = Modifier
                .padding(16.dp)
                .background(Color(0xFF00008B))
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

@Composable
fun Header7(onMenuClick: () -> Unit) {
    var showAddDialog by remember { mutableStateOf(false) }

    if (showAddDialog) {
        AddImageDialog(
            onDismiss = { showAddDialog = false },
            onAdd = { /* Handle add action */ }
        )
    }

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
            text = "Wave Dynamics",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        IconButton(
            onClick = { /* Handle import action */ },
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.FileUpload,
                contentDescription = "Import Button",
                tint = Color.White
            )
        }
        IconButton(
            onClick = { /* Handle export action */ },
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.FileDownload,
                contentDescription = "Export Button",
                tint = Color.White
            )
        }
    }
}

@Composable
fun BodyContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        ButtonSection()
        Spacer(modifier = Modifier.height(20.dp))
        Body7()
    }
}

@Composable
fun ButtonSection() {
    var showMyPresets by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { showMyPresets = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (showMyPresets) Color(0xFF00008B) else Color.Transparent,
                contentColor = if (showMyPresets) Color.White else Color.Gray
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text("My Presets", fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.width(2.dp))
        Button(
            onClick = { showMyPresets = false },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (!showMyPresets) Color(0xFF00008B) else Color.Transparent,
                contentColor = if (!showMyPresets) Color.White else Color.Gray
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text("SSA Presets", fontWeight = FontWeight.Bold)
        }
    }

    if (showMyPresets) {
        MyPresetsSection()
    } else {
        PresetsSection()
    }
}

@Composable
fun MyPresetsSection() {
    var showGymPresetDialog by remember { mutableStateOf(false) }
    var showCoffeePresetDialog by remember { mutableStateOf(false) }
    var myPresets by remember { mutableStateOf(listOf<String>()) }
    var showAddDialog by remember { mutableStateOf(false) }

    if (showAddDialog) {
        AddPresetDialog(
            onDismiss = { showAddDialog = false },
            onAdd = { newPreset ->
                myPresets = myPresets + newPreset
                showAddDialog = false
            }
        )
    }

    Column {
        myPresets.forEach { preset ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(
                    onClick = {
                        when (preset) {
                            "Gym preset" -> showGymPresetDialog = true
                            "Coffee preset" -> showCoffeePresetDialog = true
                        }
                    }
                ) {
                    Text(preset)
                }
                IconButton(onClick = {
                    myPresets = myPresets.filterNot { it == preset }
                }) {
                    Icon(imageVector = Icons.Default.Close , contentDescription = "Delete")
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        FloatingActionButton(
            onClick = { showAddDialog = true },
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Preset")
        }
    }

    if (showGymPresetDialog) {
        PresetDialog(
            imageRes = R.drawable.wavesettings, // replace with your image resource
            onDismiss = { showGymPresetDialog = false }
        )
    }

    if (showCoffeePresetDialog) {
        PresetDialog(
            imageRes = R.drawable.wavesettings, // replace with your image resource
            onDismiss = { showCoffeePresetDialog = false }
        )
    }
}

@Composable
fun AddPresetDialog(onDismiss: () -> Unit, onAdd: (String) -> Unit) {
    var presetName by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = presetName,
                    onValueChange = { presetName = it },
                    label = { Text("Preset Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    ) {
                        Text("Cancel")
                    }
                    Button(
                        onClick = {
                            onAdd(presetName)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    ) {
                        Text("Add")
                    }
                }
            }
        }
    }
}

@Composable
fun PresetsSection() {
    var showGymPresetDialog by remember { mutableStateOf(false) }
    var showCoffeePresetDialog by remember { mutableStateOf(false) }
    var presets by remember { mutableStateOf(listOf("Gym preset", "Coffee preset")) }

    if (showGymPresetDialog) {
        PresetDialog(
            imageRes = R.drawable.wavesettings, // replace with your image resource
            onDismiss = { showGymPresetDialog = false }
        )
    }

    if (showCoffeePresetDialog) {
        PresetDialog(
            imageRes = R.drawable.wavesettings, // replace with your image resource
            onDismiss = { showCoffeePresetDialog = false }
        )
    }

    Column {
        presets.forEach { preset ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(
                    onClick = {
                        when (preset) {
                            "Gym preset" -> showGymPresetDialog = true
                            "Coffee preset" -> showCoffeePresetDialog = true
                        }
                    }
                ) {
                    Text(preset)
                }
                IconButton(onClick = {
                    presets = presets.filterNot { it == preset }
                }) {
                    Icon(imageVector = Icons.Default.Close , contentDescription = "Delete")
                }
            }
        }
    }
}

@Composable
fun Body7() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
    }
}

@Composable
fun AddImageDialog(onDismiss: () -> Unit, onAdd: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.androidlogo), // replace with your image resource
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text("X")
                }
                Button(
                    onClick = onAdd,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text("Add")
                }
            }
        }
    }
}

@Composable
fun PresetDialog(imageRes: Int, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = imageRes), // replace with your image resource
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

@Preview(widthDp = 393, heightDp = 852)
@Composable
private fun HomepagePreview1() {
    MyApp7()
}
