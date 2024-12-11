package com.hanif.deteksiperson.ui.home.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BabyChangingStation
import androidx.compose.material.icons.rounded.BedroomBaby
import androidx.compose.material.icons.rounded.Videocam
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.putra.myapplication.R
import com.putra.myapplication.ui.AppScreen


@Composable
fun DashboardScreen(navController: NavController) {

    val cardData = listOf(
        CardData(Icons.Rounded.Videocam, "Live Video", "monitoring bayi", AppScreen.Dashboard.LiveVideo.route),
        CardData(Icons.Rounded.BabyChangingStation, "Deteksi Bayi", "bayi dalam bahaya",AppScreen.Dashboard.Deteksi.route),
    )

    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.background(
            brush = Brush.linearGradient(
                colors = listOf(Color(0xFF0C4B8E), Color(0xFF80D0C7)),
            ),

            )) {
            Image(modifier = Modifier.padding(20.dp).fillMaxWidth().height(200.dp), painter = painterResource(id = R.drawable.gambar), contentDescription = null)
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                repeat(2) { rowIndex ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(2) { colIndex ->
                            val index = rowIndex * 2 + colIndex
                            if (index < cardData.size) {
                                val data = cardData[index]
                                ElevatedCard(
                                    onClick = {navController.navigate(data.route)},
                                    colors = CardDefaults.cardColors(
                                        containerColor = colorResource(
                                            id = R.color.birumentah
                                        )
                                    ),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                    modifier = Modifier.size(width = 164.dp, height = 175.dp)
                                ) {
                                    Column(Modifier.padding(10.dp)) {
                                        Image(
                                            imageVector = data.icon,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .height(50.dp)
                                                .width(50.dp)
                                        )

                                        Spacer(modifier = Modifier.height(50.dp))
                                        Text(
                                            text = data.title,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(
                                            text = data.subtitle,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            } else {
                                Spacer(modifier = Modifier.size(width = 164.dp, height = 175.dp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }

    }
}


@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(navController = rememberNavController())
}


data class CardData(val icon: ImageVector, val title: String, val subtitle: String,val route : String)
