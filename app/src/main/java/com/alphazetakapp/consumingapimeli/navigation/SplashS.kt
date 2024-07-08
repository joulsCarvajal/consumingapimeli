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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import com.alphazetakapp.consumingapimeli.MainActivity
import com.alphazetakapp.consumingapimeli.R

@Composable
fun SplashS() {
    val alpha = remember { Animatable(0f) }
    val context = LocalContext.current
    LaunchedEffect(key1 = true, block = {
        alpha.animateTo(1f, animationSpec = tween(1300))
        context.startActivity(Intent(context, MainActivity::class.java))
//        delay(1000)
//        context.startActivity(Intent(context, Routes.SearchProduct::class.java))

    })
    // Get the insets for the current view
    val insets = ViewCompat.getRootWindowInsets(LocalView.current)
    val bottomInset = insets?.getInsets(WindowInsetsCompat.Type.systemBars())?.bottom ?: 0
    val additionalPadding = 32.dp // additional padding
    val bottomPadding = with(LocalDensity.current) {
        // Convert the bottom inset to dp and add the additional padding
        bottomInset.toDp() + additionalPadding
    }

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
                //modifier = Modifier.size(128.dp).clickable { navController.navigate(Routes.Login.route) }
            )
            Text(text = "Mercado Libre", color = Color.White, fontSize = 32.sp)
            Text(text = "Búsqueda", color = Color.White, fontSize = 32.sp)
        }
    }
}