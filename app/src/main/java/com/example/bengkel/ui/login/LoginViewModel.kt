package com.example.bengkel.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.bengkel.data.IRepository
import com.example.bengkel.data.source.remote.request.LoginRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class LoginViewModel(private val iRepository: IRepository, private val coroutineDispatcher: CoroutineDispatcher): ViewModel(){
    fun login(username: String, password: String) = iRepository.login(username, password).asLiveData()
    val isLogin = iRepository.isLogin().asLiveData()

    fun logout() = viewModelScope.launch(coroutineDispatcher) {
        iRepository.logout()
    }
}