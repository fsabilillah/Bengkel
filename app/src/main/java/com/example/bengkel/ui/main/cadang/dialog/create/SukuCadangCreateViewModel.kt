package com.example.bengkel.ui.main.cadang.dialog.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.bengkel.data.IRepository
import com.example.bengkel.data.source.remote.request.SukuCadangRequest

class SukuCadangCreateViewModel(private val iRepository: IRepository) : ViewModel() {
    fun createSukuCadang(sukuCadangRequest: SukuCadangRequest) = iRepository.createSukuCadang(sukuCadangRequest).asLiveData()
}