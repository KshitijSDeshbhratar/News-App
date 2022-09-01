package com.example.mocknewsapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.example.mocknewsapp.model.Article
import com.example.mocknewsapp.model.NewsResponse
import com.example.mocknewsapp.repository.MyRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MyViewModel constructor(private val repository: MyRepository) : ViewModel() {

        val breakingNews = MutableLiveData<List<NewsResponse>>()
        val userArticles =MutableLiveData<List<NewsResponse>>()
        val errorMessage =MutableLiveData<String>()


        fun breakingNews(){
                    val response = repository.breakingNews()
                    response.enqueue(object : Callback<Article?> {
                            override fun onResponse(
                                    call: Call<Article?>,
                                    response: Response<Article?>
                            ) {
                                    Log.d("asbj", "sn ${response}")
                                    breakingNews.postValue(response.body()?.list?.toMutableList())
                            }

                            override fun onFailure(call: Call<Article?>, t: Throwable) {
                                    Log.d("erOR", "sn ${t.message}")

                                    errorMessage.postValue(t.message)
                            }
                    })
        }

        fun getArticles() {
                val response = repository.getArticles()
                response.enqueue(object : retrofit2.Callback<Article> {
                        override fun onResponse(
                                call: Call<Article>,
                                response: Response<Article>
                        ) {
                                Log.d("asbj", "sn ${response.body()}")
                                userArticles.postValue(response.body()?.list?.toMutableList())
                        }

                        override fun onFailure(call: Call<Article>, t: Throwable) {
                                Log.d("erOR", "sn ${t.message}")
                                errorMessage.postValue(t.message)
                        }
                })
        }



}