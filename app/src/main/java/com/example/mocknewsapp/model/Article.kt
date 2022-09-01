package com.example.mocknewsapp.model

import com.google.gson.annotations.SerializedName

data class Article(

    @SerializedName("articles")
    var list: List<NewsResponse>,
    /*var list1: List<NewsResponse>*/
)
