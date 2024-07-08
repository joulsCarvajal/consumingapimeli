package com.alphazetakapp.consumingapimeli.searcher.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphazetakapp.consumingapimeli.core.network.ApiService
import com.alphazetakapp.consumingapimeli.searcher.data.SearchProductItemResponse
import kotlinx.coroutines.launch

class SearchProductViewModel(private val apiService: ApiService) : ViewModel() {

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery : LiveData<String> = _searchQuery

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _productList = MutableLiveData<List<SearchProductItemResponse>>()
    val productList: LiveData<List<SearchProductItemResponse>> = _productList

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage : MutableLiveData<String?> = _errorMessage

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun searchProducts() {
        if (searchQuery.value?.isNotEmpty() == true) {
            _isLoading.value = true
            viewModelScope.launch {
                try {
                    val response = apiService.getSearchproduct(searchQuery.value!!)
                    if (response.isSuccessful) {
                        _productList.value = response.body()?.product ?: emptyList()
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
}