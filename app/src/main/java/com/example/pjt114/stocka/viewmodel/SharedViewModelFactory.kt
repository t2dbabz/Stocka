package com.example.pjt114.stocka.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pjt114.stocka.repository.AppRepository

class SharedViewModelFactory(val appRepository: AppRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SharedViewModel(appRepository) as T
    }

}