package com.hanif.deteksiperson.ui.home.nav

import androidx.navigation.NavBackStackEntry
import com.putra.myapplication.ui.AppScreen


fun NavBackStackEntry.getTitle(): Int {
    return when (destination.parent?.route) {
        AppScreen.Dashboard.route -> AppScreen.Dashboard.title
        else -> AppScreen.Dashboard.title
    }
}