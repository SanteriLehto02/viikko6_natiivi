package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScaffoldApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController){

    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(title)},
        actions = {
            IconButton(
                onClick = {
                    expanded = !expanded
                }
            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false}
            ) {
                DropdownMenuItem(
                    text = { Text("Info") },
                    onClick = { navController.navigate("info") }
                )
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = { navController.navigate("settings") }
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController){
    TopAppBar(
        title = { Text(title)},
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp()}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreed(navController: NavController){
    Scaffold(
        topBar = { MainTopBar("My App", navController)},
        content = { innerPadding ->
            // Use the innerPadding here to ensure the content is not covered by the TopAppBar
            Text(
                text = "Content for Home Screen",
                modifier = Modifier.padding(innerPadding) // Add padding
            )
        },
    )
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InfoScreed(navController: NavController){
    Scaffold(
        topBar = { ScreenTopBar("Info", navController) },
        content = { innerPadding ->
            Text(
                text = "Content for Info Screen",
                modifier = Modifier.padding(innerPadding) // Add padding
            )
        },
    )
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavController){
    Scaffold(
        topBar = { ScreenTopBar("Settings", navController) },
        content = { innerPadding ->
            Text(
                text = "Content for Settings Screen",
                modifier = Modifier.padding(innerPadding) // Add padding
            )
        }
    )
}

@Composable
fun ScaffoldApp(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable(route = "Home"){
            MainScreed(navController)
        }
        composable(route = "Info"){
            InfoScreed(navController)
        }
        composable(route = "Settings"){
            SettingsScreen(navController)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}