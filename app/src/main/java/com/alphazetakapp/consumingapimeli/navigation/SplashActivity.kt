package com.alphazetakapp.consumingapimeli.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.alphazetakapp.consumingapimeli.ui.theme.ConsumingapimeliTheme

@SuppressLint("CustomSplashScreen")
class SplashActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConsumingapimeliTheme {
                SplashS()
            }
        }
    }
}

