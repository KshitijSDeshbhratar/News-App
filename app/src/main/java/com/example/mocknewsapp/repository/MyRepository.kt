package com.example.mocknewsapp.repository

import com.example.mocknewsapp.model.Article
import com.example.mocknewsapp.newsApi.ApiService
import com.example.mocknewsapp.newsApi.ArticleService

class MyRepository(private val apiService: ApiService, private val articleService: ArticleService){

        fun breakingNews() =apiService.breakingNews()
        fun getArticles()  =articleService.getArticles()






}