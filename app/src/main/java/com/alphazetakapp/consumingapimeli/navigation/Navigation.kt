package com.alphazetakapp.consumingapimeli.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alphazetakapp.consumingapimeli.searcher.ui.SearchProduct
import com.alphazetakapp.consumingapimeli.core.network.ApiService
import com.alphazetakapp.consumingapimeli.detail.ui.DetailProduct

@Composable
fun App(apiService: ApiService) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.SearchProduct.route //Aquí es donde comienza mi navegación
    ) {
        composable(Routes.Splash.route) { SplashS() }
        composable(Routes.SearchProduct.route) { SearchProduct(apiService, navController) }
        composable(
            route = Routes.DetailProduct.route,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            DetailProduct(
                productId = productId,
                apiService = apiService,
                navController = navController
            )
        }
    }
}