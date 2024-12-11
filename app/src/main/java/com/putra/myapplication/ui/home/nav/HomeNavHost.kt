package com.hanif.deteksiperson.ui.home.nav

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.firebase.auth.FirebaseAuth
import com.hanif.deteksiperson.ui.home.dashboard.CctvScreen
import com.hanif.deteksiperson.ui.home.dashboard.DashboardScreen
import com.hanif.deteksiperson.ui.home.dashboard.DeteksiBayi
import com.hanif.deteksiperson.ui.home.profil.ProfileScreen
import com.hanif.deteksiperson.ui.utils.startNewActivity
import com.itextpdf.layout.element.Text
import com.putra.myapplication.ui.AppScreen
import com.putra.myapplication.ui.auth.AuthActivity
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeNavHost() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val title = remember { mutableStateOf(AppScreen.Dashboard.title) }


    val bottomNavigationItems = listOf(
        AppScreen.Dashboard,
        AppScreen.Profil
    )
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.background(Color.White),
        content = { MainScreenNavigationConfigurations(navController = navController) },
        bottomBar = {
            BottomAppBar(
                bottomMenuList = bottomNavigationItems,
                navController = navController,
                context,
            )
        },
//        isFloatingActionButtonDocked = true,
//        floatingActionButtonPosition = FabPosition.Center,
//        floatingActionButton = { FloatingActionButtonSample() }
    )
}

@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.Dashboard.route
    ) {
        navigation(
            startDestination = AppScreen.Dashboard.Home.route,
            route = AppScreen.Dashboard.route
        ) {
            composable(AppScreen.Dashboard.Home.route) {
                DashboardScreen(navController = navController)
            }
            composable(AppScreen.Dashboard.LiveVideo.route) {
                CctvScreen(navController = navController)
            }


            composable(AppScreen.Dashboard.Deteksi.route) {
                DeteksiBayi(navController = navController, hiltViewModel())
            }
        }
        composable(AppScreen.Profil.route) {
            val auth = FirebaseAuth.getInstance()
            val context = LocalContext.current
            auth.signOut()
            context.startNewActivity(AuthActivity::class.java)

            ProfileScreen(navController)
        }
        // Tambahkan blok navigation dan composable lain di sini jika diperlukan
    }
}


@Composable
fun BottomAppBar(
    bottomMenuList: List<AppScreen>,
    navController: NavHostController,
    context: Context
) {
    val currentRoute = currentRoute(navController)
    BottomNavigation(backgroundColor = Color(0xFF0C4B8E)) {
        bottomMenuList.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = stringResource(id = screen.title)
                    )
                },
                label = { Text(stringResource(id = screen.title)) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White,
                alwaysShowLabel = true,
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FloatingActionButtonSample() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    FloatingActionButton(onClick = {
        scope.launch {
            when (scaffoldState.snackbarHostState.showSnackbar(
                message = "Jetpack Compose Snackbar",
                actionLabel = "Ok"
            )) {
                SnackbarResult.Dismissed ->
                    Log.d("TAB", "Dismissed")

                SnackbarResult.ActionPerformed ->
                    Log.d("TAB", "Action!")
            }
        }
    }) {
        Text("X")
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString("KEY_ROUTE")
}