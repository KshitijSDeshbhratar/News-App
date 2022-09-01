package com.example.mocknewsapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mocknewsapp.R
import com.example.mocknewsapp.model.NewsResponse
import com.example.mocknewsapp.ui.NewsFragmentDirections
import com.example.mocknewsapp.viewModel.MyViewModel

class MyAdapter (mainView : View,vm:MyViewModel): RecyclerView.Adapter<MyAdapter.ViewHolder>(){

        var users = mutableListOf<NewsResponse>()
        var theView = mainView
        var vm = vm

    fun setUserList(users : List<NewsResponse>){
        this.users = users.toMutableList()
        Log.e("ksd", " My Adapter Log${users.toString()}")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.newslist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val user = users[position]
            holder.bindView(user)
            holder.itemView.setOnClickListener {
                val action = NewsFragmentDirections.actionNewsToArticle(position)
//                Navigation.findNavController(mainView).navigate(R.id.action_news_to_description)
                Navigation.findNavController(holder.itemView).navigate(action)
            }
           // holder.clickable(theView,position,vm)

    }

    override fun getItemCount(): Int {
        return users.size
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        private val imageView: ImageView = itemView.findViewById(R.id.imageView1)
        private val textView: TextView = itemView.findViewById(R.id.title)
        private val textView1: TextView = itemView.findViewById(R.id.time)

     /*   fun clickable(mainView: View,num:Int,vm : MyViewModel) {
            this.itemView.findViewById<CardView?>(R.id.CardView1).setOnClickListener {
                val action = NewsFragmentDirections.actionNewsToArticle(num)
//                Navigation.findNavController(mainView).navigate(R.id.action_news_to_description)
                Navigation.findNavController(mainView).navigate(action)
            }*/
        //}

        fun bindView(apiUser: NewsResponse) {
            textView.text = apiUser.title
            textView1.text = "Published at - ${apiUser.time}"
            Glide.with(itemView.context)
                .load(
                    apiUser.urlToImage
                ).centerCrop()
                .into(imageView)
        }



    }


}