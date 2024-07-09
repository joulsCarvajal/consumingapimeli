package com.alphazetakapp.consumingapimeli.searcher.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.alphazetakapp.consumingapimeli.core.network.ApiService
import com.alphazetakapp.consumingapimeli.searcher.data.SearchProductItemResponse
import com.alphazetakapp.consumingapimeli.searcher.data.SearchProductDataResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okio.IOException
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.BDDMockito.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
class SearchProductViewModelTest {

    @get:Rule
    var instantExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var apiService: ApiService
    private lateinit var viewModel: SearchProductViewModel

    @Before
    fun setup() {
        apiService = mock()
        viewModel = SearchProductViewModel(apiService)
    }

    @Test
    fun `searchProducts should update productList on success`() = runBlockingTest {
        val mockResponse = mock<SearchProductDataResponse>()
        val mockProductList = listOf(SearchProductItemResponse("MLA810645375", "Motorola G6 Plus 64 Gb Nimbus", "MLA1055","http://mla-s2-p.mlstatic.com/795558-MLA31003306206_062019-I.jpg", "MLA-CELLPHONES", 14.899))

        whenever(apiService.getSearchproduct("query")).thenReturn(Response.success(mockResponse))
        whenever(mockResponse.product).thenReturn(mockProductList)

        val observer: Observer<List<SearchProductItemResponse>> = mock()
        viewModel.productList.observeForever(observer)

        viewModel.onSearchQueryChange("query")
        viewModel.searchProducts()

        verify(observer).onChanged(mockProductList)
    }

    @Test
    fun `searchProducts should set errorMessage on failure`() = runBlockingTest {
        val errorMessage = "Network error"

        whenever(apiService.getSearchproduct("query")).thenThrow(IOException(errorMessage))

        val observer: Observer<String?> = mock()
        viewModel.errorMessage.observeForever(observer)

        viewModel.onSearchQueryChange("query")
        viewModel.searchProducts()

        verify(observer).onChanged("Error: $errorMessage")
    }
}