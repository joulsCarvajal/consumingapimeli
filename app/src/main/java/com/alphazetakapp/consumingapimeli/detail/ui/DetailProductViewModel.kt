package com.alphazetakapp.consumingapimeli.detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphazetakapp.consumingapimeli.core.network.ApiService
import com.alphazetakapp.consumingapimeli.searcher.data.SearchProductItemResponse
import kotlinx.coroutines.launch

class DetailProductViewModel(private val apiService: ApiService) : ViewModel() {

    private val _product = MutableLiveData<SearchProductItemResponse?>()
    val product: LiveData<SearchProductItemResponse?> = _product

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun fetchProduct(productId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = apiService.getProductDetail(productId)
                if (response.isSuccessful) {
                    _product.value = response.body()
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}