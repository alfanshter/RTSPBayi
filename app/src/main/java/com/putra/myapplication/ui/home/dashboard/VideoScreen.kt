package com.hanif.deteksiperson.ui.home.dashboard

import android.content.pm.ActivityInfo
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hanif.deteksiperson.ui.utils.Constant
import com.putra.myapplication.R
import com.putra.myapplication.ui.AppScreen


@Composable
fun CctvScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as? ComponentActivity
    VideoPlayerScreen()
}

@Composable
fun VideoPlayerScreen() {
    val context = LocalContext.current
    val videoUrl = "rtsp://172.20.10.14:8554/live" // Ganti dengan URL RTSP Anda

    // Inisialisasi ExoPlayer
    val player = remember { ExoPlayer.Builder(context).build() }

    // Memuat media
    val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
    player.setMediaItem(mediaItem)
    player.prepare()
    player.playWhenReady = true

    // Menggunakan PlayerView untuk menampilkan video
    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { PlayerView(context).apply { this.player = player } },
            modifier = Modifier.fillMaxSize()
        )
    }

    // Membersihkan player saat komponen dihapus
    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }
}