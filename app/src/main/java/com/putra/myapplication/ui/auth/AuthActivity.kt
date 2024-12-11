package com.putra.myapplication.ui.auth

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.putra.myapplication.ui.BaseActivity
import com.putra.myapplication.ui.theme.DeteksiBayiTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeteksiBayiTheme {
                AuthNavHost(rememberNavController())
            }
        }
    }
}