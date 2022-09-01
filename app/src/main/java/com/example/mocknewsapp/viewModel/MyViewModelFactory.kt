package com.example.mocknewsapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mocknewsapp.repository.MyRepository
import java.lang.IllegalArgumentException

class MyViewModelFactory constructor(private val repository: MyRepository): ViewModelProvider.Factory {

        override fun<T: ViewModel> create(modelClass: Class<T>) : T{
            return if (modelClass.isAssignableFrom(MyViewModel::class.java)){
                MyViewModel(this.repository) as T
    }       else{
            throw IllegalArgumentException("View Model not FOund")
        }

    }
}