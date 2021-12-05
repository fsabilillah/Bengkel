package com.example.bengkel.ui.main.service.usage

import androidx.lifecycle.ViewModel
import com.example.bengkel.data.IRepository
import androidx.lifecycle.asLiveData

class UsageViewModel(private val iRepository: IRepository) : ViewModel() {

    var listSukuCadang = HashMap<String, String>()

    fun usage(id: String) = iRepository.getPemakaian(id).asLiveData()
    fun createUsage(id: String, idSukuCadang: String, jumlahSukuCadang: String) = iRepository.createPemakaian(id, idSukuCadang, jumlahSukuCadang).asLiveData()
    fun updateUsage(idService: String, idPakai: String,  idSukuCadang: String, jumlahSukuCadang: String) = iRepository.updatePemakaian(idService, idPakai, idSukuCadang, jumlahSukuCadang).asLiveData()
    fun deleteUsage(id: String, idPakai: String) = iRepository.deletePemakaian(id, idPakai).asLiveData()
}