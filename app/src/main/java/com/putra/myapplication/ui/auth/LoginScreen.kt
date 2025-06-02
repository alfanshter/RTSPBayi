package com.putra.myapplication.ui.auth

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hanif.detectionperson.data.Resource
import com.hanif.detectionperson.ui.faker.FakeViewModelProvider
import com.hanif.deteksiperson.ui.home.HomeActivity
import com.hanif.deteksiperson.ui.utils.startNewActivity
import com.ptpws.agrogontafarm.ui.common.poppinsFamily
import com.putra.myapplication.R
import com.putra.myapplication.ui.theme.DeteksiBayiTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(viewModel: AuthViewModel, navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    val loginFlow = viewModel.loginFlow.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = BringIntoViewRequester()
    val context = LocalContext.current

    Surface(
        color = colorResource(id = R.color.white),
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.bgbayi), contentDescription = null, modifier =
                    Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds
            )

            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(Modifier.height(30.dp))
                Image(
                    painter = painterResource(id = R.drawable.bayi), contentDescription = "",
                    modifier = Modifier.size(200.dp)
                )
                Spacer(Modifier.height(30.dp))

                Text(
                    text = "MONITORING BAYI",
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))


                Spacer(modifier = Modifier.height(30.dp))
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text(text = "Masukkan Email") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text(text = "Masukkan Password") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.login(email, password) },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                ) {
                    Text(text = "Login")
                }

                Spacer(modifier = Modifier.height(16.dp))

                loginFlow.value?.let {
                    when (it) {
                        is Resource.Failure -> {
                            Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                        }

                        is Resource.Success -> {
                            LaunchedEffect(Unit) {
                                context.startNewActivity(HomeActivity::class.java)
                            }
                        }

                        Resource.Loading -> {
                            CircularProgressIndicator()
                        }

                        else -> {}
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    DeteksiBayiTheme {
        LoginScreen(FakeViewModelProvider.provideAuthViewModel(), rememberNavController())
    }
}


