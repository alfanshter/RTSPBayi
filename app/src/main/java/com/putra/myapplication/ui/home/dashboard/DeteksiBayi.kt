package com.hanif.deteksiperson.ui.home.dashboard

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hanif.detectionperson.data.Resource
import com.hanif.deteksiperson.data.model.LampuModel
import com.hanif.deteksiperson.ui.home.viewmodel.LampuViewModel
import com.ptpws.agrogontafarm.data.models.BayiModel
import com.putra.myapplication.R
import com.putra.myapplication.ui.AppScreen
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Preview
@Composable
fun DeteksiBayiPrev() {

    DeteksiBayi(navController = rememberNavController(), hiltViewModel())
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeteksiBayi(navController: NavController, lampuViewModel: LampuViewModel) {

    //load viewmodel
    val bayidata by lampuViewModel.bayiState.collectAsState()

    var event: String? = null
    var tanggal: String? = null
    var waktu: String? = null

    Log.d("polinema", "DeteksiBayi: $bayidata")



    Surface(
        color = Color.White, modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF0C4B8E), Color(0xFF80D0C7)),
                    ),

                    )
                .padding(bottom = 60.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Spacer(Modifier.height(40.dp))

                    Text(
                        text = "Deteksi Bayi Dalam Bahaya",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(Modifier.height(40.dp))

                    when(bayidata){
                        is  Resource.Success -> {
                            val data = (bayidata as Resource.Success<List<BayiModel>>).result
                            data.forEach { bayiModel ->

                                ElevatedCard(
                                    onClick = { navController.navigate(AppScreen.Dashboard.LiveVideo.route) },
                                    colors = CardDefaults.cardColors(
                                        containerColor = colorResource(
                                            id = R.color.birumentah
                                        )
                                    ),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                                ) {
                                    Column(
                                        Modifier
                                            .padding(10.dp)
                                            .fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {

                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(
                                            text = "Event : ${bayiModel.event}",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.Center

                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(
                                            text = "Tanggal : ${exportDate(bayiModel.created_at!!)}",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.Center

                                        )
                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = "Waktu : ${exportHours(bayiModel.created_at!!)}",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.Center

                                        )


                                    }
                                }


                            }

                        }

                        is Resource.Loading -> {

                        }

                        is  Resource.Error -> {

                        }
                        else -> {}
                    }


                }
            }
        }



    }


}


fun exportHours(timestamp : String) : String {

    // Convert the timestamp string to a LocalDateTime object
    val dateTime = LocalDateTime.parse(timestamp)

    // Define the desired format for time
    val formatter = DateTimeFormatter.ofPattern("HH:mm")

    // Format the LocalDateTime object into the desired time format
    val formattedTime = dateTime.format(formatter)

    return  formattedTime
}

fun exportDate(timestamp: String)  : String {
    val timestamp = "2024-12-11T13:54:22.543122"

    // Convert the timestamp string to a LocalDateTime object
    val dateTime = LocalDateTime.parse(timestamp)

    // Define the desired format
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    // Format the LocalDateTime object into the desired string format
    val formattedDate = dateTime.format(formatter)

    return formattedDate
}