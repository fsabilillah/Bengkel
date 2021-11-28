package com.example.bengkel.ui.main.cadang.dialog.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.bengkel.data.IRepository
import com.example.bengkel.data.source.remote.request.UpdateSukuCadangRequest

class SukuCadangUpdateViewModel(private val iRepository: IRepository) : ViewModel() {
    fun updateSukuCadang(id: Int, sukuCadangRequest: UpdateSukuCadangRequest) = iRepository.updateSukuCadang(id, sukuCadangRequest).asLiveData()
}