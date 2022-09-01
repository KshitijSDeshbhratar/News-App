package com.example.mocknewsapp.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mocknewsapp.R
import com.example.mocknewsapp.newsApi.ApiService
import com.example.mocknewsapp.newsApi.ArticleService
import com.example.mocknewsapp.repository.MyRepository
import com.example.mocknewsapp.viewModel.MyViewModel
import com.example.mocknewsapp.viewModel.MyViewModelFactory


class ArticlesFragment : Fragment() {

    private val TAG = "ARTICLES"
    lateinit var viewModel: MyViewModel
    private val retrofitService = ApiService.getInstance()
    private val articleService = ArticleService.getInstance()

   /* private class Views(rootView: View){
        val author :TextView = rootView.findViewById(R.id.author)
        val description :TextView = rootView.findViewById(R.id.Description)
        val heading :TextView = rootView.findViewById(R.id.heading)
        val imageView:ImageView = rootView.findViewById(R.id.imageView1)
    }*/

/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(MyRepository(retrofitService,articleService))
        )[MyViewModel::class.java]
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v:View = inflater.inflate(R.layout.fragment_articles,container,false)
        viewModel =ViewModelProvider(this,
        MyViewModelFactory(MyRepository(retrofitService,articleService)))
            .get(MyViewModel::class.java)

        viewModel.userArticles.observe(viewLifecycleOwner,Observer{
            Log.d(TAG, "onCreate: $it")
            val args: ArticlesFragmentArgs by navArgs()
            val index:Int = args.promocode
            v.findViewById<TextView>(R.id.author)
                .setText(it.get(index).author)
            v.findViewById<TextView>(R.id.Description)
                .setText(it.get(index).description)
            v.findViewById<TextView>(R.id.heading)
                .setText(it.get(index).title)
            v.findViewById<ImageView>(R.id.imageView1)
            Glide.with(v)
                .load(
                    it.get(index).urlToImage
                ).centerCrop()
                .into(v.findViewById(R.id.imageView1))

        })
        viewModel.errorMessage.observe(viewLifecycleOwner,Observer{

        })
        viewModel.getArticles()
        return v


    }
}