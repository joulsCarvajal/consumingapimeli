package com.alphazetakapp.consumingapimeli.core.network

import com.alphazetakapp.consumingapimeli.searcher.data.SearchProductDataResponse
import com.alphazetakapp.consumingapimeli.searcher.data.SearchProductItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/sites/MLA/search")
    suspend fun getSearchproduct(@Query("q") productName:String): Response<SearchProductDataResponse>

    @GET("/items/{productId}")
    suspend fun getProductDetail(@Path("productId") productId: String): Response<SearchProductItemResponse>
}