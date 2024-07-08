package com.alphazetakapp.consumingapimeli.searcher.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.alphazetakapp.consumingapimeli.core.network.ApiService
import com.alphazetakapp.consumingapimeli.searcher.data.SearchProductItemResponse
import kotlinx.coroutines.launch
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun SearchProduct(
    apiService: ApiService,
    navController: NavController,
    viewModel: SearchProductViewModel = viewModel { SearchProductViewModel(apiService) }
) {

    val searchQuery by viewModel.searchQuery.observeAsState("")
    val isLoading by viewModel.isLoading.observeAsState(false)
    val productList by viewModel.productList.observeAsState(emptyList())
    val errorMessage by viewModel.errorMessage.observeAsState(null)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Input field for search query
        OutlinedTextField(value = searchQuery,
            onValueChange = {
                viewModel.onSearchQueryChange(it) // Actualiza el ViewModel con la nueva consulta
                viewModel.searchProducts() // Inicia la búsqueda de productos
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = { Text("Search") },
            placeholder = { Text("Enter product name") })

        Spacer(modifier = Modifier.height(8.dp))

        // Muestra un indicador de carga si se está buscando
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            // Muestra la lista de productos encontrados
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(productList) { product ->
                    SearchProductItem(product = product, navController)
                }
            }
        }
        // Muestra un mensaje de error si ocurrió algún problema
        errorMessage?.let {
            Text(
                text = it, color = Color.Red, modifier = Modifier.padding(16.dp)
            )
        }
    }
}
