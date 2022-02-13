package com.example.bengkel.ui.main.biaya

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.bengkel.data.IRepository

class BiayaViewModel(private val iRepository: IRepository) : ViewModel() {
    val biaya = iRepository.getBiayaTambahan().asLiveData()
    fun createBiaya(biaya: String) = iRepository.createBiayaTambahan(biaya).asLiveData()
    fun updateBiaya(id: String, biaya: String) = iRepository.updateBiayaTambahan(id, biaya).asLiveData()
    fun deleteBiaya(id: String) = iRepository.deleteBiayaTambahan(id).asLiveData()
}