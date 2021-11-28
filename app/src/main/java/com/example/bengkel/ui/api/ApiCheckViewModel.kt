package com.example.bengkel.ui.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.bengkel.data.IRepository

class ApiCheckViewModel(private val iRepository: IRepository): ViewModel() {
    val healthCheck = iRepository.health().asLiveData()
}