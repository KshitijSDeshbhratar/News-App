package com.example.mocknewsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mocknewsapp.R
import com.example.mocknewsapp.adapter.MyAdapter
import com.example.mocknewsapp.newsApi.ApiService
import com.example.mocknewsapp.newsApi.ArticleService
import com.example.mocknewsapp.repository.MyRepository
import com.example.mocknewsapp.viewModel.MyViewModel
import com.example.mocknewsapp.viewModel.MyViewModelFactory


class NewsFragment : Fragment() {

    private val TAG = "NEWS"
    lateinit var viewModel: MyViewModel
    private val retrofitService = ApiService.getInstance()
    private val articleService = ArticleService.getInstance()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val v :View = inflater.inflate(R.layout.fragment_news,container,false)
        viewModel =ViewModelProvider(this,MyViewModelFactory(MyRepository(retrofitService,articleService))).get(MyViewModel::class.java)
        val adapter= MyAdapter(v,viewModel)
        val rv: RecyclerView = v.findViewById(R.id.recyclerView1)
        rv.adapter = adapter
        rv.layoutManager=LinearLayoutManager(context)

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {
            Log.d(TAG,"onCreate: $it")
            adapter.setUserList(it)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
        })
        viewModel.breakingNews()
        return v

    }


}