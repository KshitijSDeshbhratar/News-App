package com.example.mocknewsapp.newsApi

import com.example.mocknewsapp.model.Article
import com.example.mocknewsapp.model.NewsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("top-headlines?country=in&apiKey=3e404cfd62424f7889d7cba0f788ec4d")
    fun breakingNews() :Call<Article>
    fun getArticles()  : Call<Article>

    companion object {
        var apiService: ApiService? = null

        fun getInstance(): ApiService {
            if (apiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService!!
        }
    }

}