package com.alphazetakapp.consumingapimeli.searcher.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.alphazetakapp.consumingapimeli.navigation.Routes
import com.alphazetakapp.consumingapimeli.searcher.data.SearchProductItemResponse

@Composable
fun SearchProductItem(product: SearchProductItemResponse, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate(Routes.DetailProduct.createRoute(product.productId)) }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // ImageView para la imagen del producto
            Image(
                painter = rememberImagePainter(product.thumbnail),
                contentDescription = "Product Thumbnail",
                modifier = Modifier
                    .size(64.dp)
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Texto con el nombre del producto
            Text(
                text = product.title,
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }
}
