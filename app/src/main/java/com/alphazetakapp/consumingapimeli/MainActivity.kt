package com.alphazetakapp.consumingapimeli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import com.alphazetakapp.consumingapimeli.navigation.App
import com.alphazetakapp.consumingapimeli.core.network.ApiService
import com.alphazetakapp.consumingapimeli.ui.theme.ConsumingapimeliTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.mercadolibre.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConsumingapimeliTheme {
                Surface(color = Color.White) {
                    App(apiService)
                }
            }
        }
    }
}

