package com.alphazetakapp.consumingapimeli.navigation

sealed class Routes(val route: String) {
    data object Splash: Routes("splash")
    data object SearchProduct: Routes ("mainScreen")
    data object DetailProduct: Routes("detailScreen/{productId}") {
        fun createRoute(productId: String) = "detailScreen/$productId"
    }
}