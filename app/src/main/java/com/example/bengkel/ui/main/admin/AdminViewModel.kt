package com.example.bengkel.ui.main.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.bengkel.data.IRepository

class AdminViewModel(private val iRepository: IRepository) : ViewModel() {
    val user = iRepository.getUser().asLiveData()
    fun deleteUser(id: Int) = iRepository.deleteUser(id).asLiveData()
}