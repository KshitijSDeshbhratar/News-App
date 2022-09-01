package com.example.mocknewsapp.newsApi

import com.example.mocknewsapp.model.Article
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ArticleService {

    @GET("top-headlines?country=in&apiKey=3e404cfd62424f7889d7cba0f788ec4d")
    fun getArticles()  : Call<Article>

    companion object {
        var articleService: ArticleService? = null

        fun getInstance() : ArticleService {
            if (articleService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                articleService = retrofit.create(ArticleService::class.java)
            }
            return articleService!!
        }
    }
}