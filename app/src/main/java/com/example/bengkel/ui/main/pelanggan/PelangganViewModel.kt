package com.example.bengkel.ui.main.pelanggan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.bengkel.data.IRepository

class PelangganViewModel(private val iRepository: IRepository): ViewModel() {
    val user = iRepository.getPelanggan().asLiveData()
    fun deleteUser(id: String) = iRepository.deletePelanggan(id).asLiveData()
    fun createUser(nama: String, noHp: String, alamat: String) = iRepository.createPelanggan(nama, noHp, alamat).asLiveData()
    fun updateUser(id: String, nama: String, noHp: String, alamat: String) = iRepository.updatePelanggan(id, nama, noHp, alamat).asLiveData()
}