package com.example.bengkel.ui.main.cadang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.bengkel.data.IRepository

class SukuCadangViewModel(private val iRepository: IRepository) : ViewModel() {
    val getSukuCadang = iRepository.getSukuCadang().asLiveData()

    fun deleteSukuCadang(id: Int) =  iRepository.deleteSukuCadang(id).asLiveData()
}