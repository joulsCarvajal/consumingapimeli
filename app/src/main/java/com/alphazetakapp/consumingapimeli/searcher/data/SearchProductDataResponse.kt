package com.alphazetakapp.consumingapimeli.searcher.data

import com.google.gson.annotations.SerializedName

data class SearchProductDataResponse(
    @SerializedName("site_id") val siteId: String,
    @SerializedName("results") val product: List<SearchProductItemResponse>
)

data class SearchProductItemResponse(
    @SerializedName("id") val productId: String,
    @SerializedName("title") val title: String,
    @SerializedName("category_id") val categoryId:String,
    @SerializedName("thumbnail") val thumbnail:String,
    @SerializedName("domain_id") val domainId:String,
    @SerializedName("price") val price:Double
)
