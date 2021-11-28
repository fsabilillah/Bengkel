package com.example.bengkel.ui.main.admin.dialog.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.bengkel.data.IRepository
import com.example.bengkel.data.source.remote.request.UserRequest

class AdminCreateDialogViewModel(private val iRepository: IRepository): ViewModel() {
    fun createUser(userRequest: UserRequest) = iRepository.createUser(userRequest).asLiveData()
}