package com.hanif.deteksiperson.ui.home.profil

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController) {
    // Your Profile screen UI here
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        Text("Profile Screen", modifier = Modifier.padding(16.dp))
    }
}