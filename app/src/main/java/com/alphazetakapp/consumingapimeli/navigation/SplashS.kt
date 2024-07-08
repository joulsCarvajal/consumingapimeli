package com.alphazetakapp.consumingapimeli.navigation

import android.content.Intent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alphazetakapp.consumingapimeli.MainActivity
import com.alphazetakapp.consumingapimeli.R

@Composable
fun SplashS() {
    val alpha = remember { Animatable(0f) }
    val context = LocalContext.current
    LaunchedEffect(key1 = true, block = {
        alpha.animateTo(1f, animationSpec = tween(1300))
        context.startActivity(Intent(context, MainActivity::class.java))
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF007EB4))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.handsclap),
                contentDescription = "Escudo",
                modifier = Modifier
                    .size(120.dp)
                    .alpha(alpha.value)
            )
            Text(text = "Mercado Libre", color = Color.White, fontSize = 32.sp)
            Text(text = "Búsqueda", color = Color.White, fontSize = 32.sp)
        }
    }
}