package com.example.bengkel.ui.main.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.bengkel.data.IRepository

class HistoryViewModel(private val iRepository: IRepository) : ViewModel() {
    val history = iRepository.getServiceDone().asLiveData()
}