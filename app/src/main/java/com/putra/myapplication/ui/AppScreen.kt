package com.putra.myapplication.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material.icons.rounded.VideoLibrary
import androidx.compose.material.icons.rounded.Videocam
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.ui.graphics.vector.ImageVector
import com.putra.myapplication.R

sealed class AppScreen(@StringRes val title: Int,  val icon: ImageVector, val route: String) {
    object Auth : AppScreen(R.string.app_name, Icons.Rounded.Security, "nav_auth") {
        object Login : AppScreen(R.string.login, Icons.Rounded.Security, "login")
    }


    object Dashboard : AppScreen(R.string.dashboard, Icons.Rounded.Home, "nav_dashboard"){
        object Home : AppScreen(R.string.dashboard, Icons.Rounded.Home, "dashboard")
        object LiveVideo : AppScreen(R.string.video, Icons.Rounded.Videocam, "video")

        object Deteksi : AppScreen(R.string.deteksi, Icons.Rounded.Warning, "deteksi")

    }

    object Profil : AppScreen(R.string.logout, Icons.Rounded.Person, "nav_profil")

}