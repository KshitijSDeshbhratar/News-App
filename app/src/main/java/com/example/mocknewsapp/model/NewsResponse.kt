package com.example.mocknewsapp.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("title")
    val title: String ,
    @SerializedName("urlToImage")
    val urlToImage: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("author")
    val author:String,
    @SerializedName("publishedAt")
    val time: String

)
