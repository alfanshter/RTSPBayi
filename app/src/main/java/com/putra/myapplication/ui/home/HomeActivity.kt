package com.hanif.deteksiperson.ui.home

import android.os.Bundle
import androidx.activity.compose.setContent
import com.hanif.deteksiperson.ui.home.nav.HomeNavHost
import com.putra.myapplication.ui.BaseActivity
import com.putra.myapplication.ui.theme.DeteksiBayiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeteksiBayiTheme {
                HomeNavHost()
            }
        }
    }
}