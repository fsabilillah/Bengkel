package com.example.bengkel.ui.main.admin.dialog.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.bengkel.data.IRepository
import com.example.bengkel.data.source.remote.request.UserRequest

class AdminUpdateDialogViewModel(private val iRepository: IRepository): ViewModel() {
    fun updateUser(id: Int, userRequest: UserRequest)= iRepository.updateUser(id, userRequest).asLiveData()
}