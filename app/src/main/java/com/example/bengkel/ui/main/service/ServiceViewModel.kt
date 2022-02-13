package com.example.bengkel.ui.main.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.bengkel.data.IRepository
import com.example.bengkel.data.Resource
import com.example.bengkel.data.source.remote.request.ServiceCreateRequest
import com.example.bengkel.data.source.remote.request.ServiceUpdateRequest

class ServiceViewModel(private val iRepository: IRepository) : ViewModel() {
    var listCustomer = HashMap<String, String>()
    var listBiaya = HashMap<String, String>()
    val service = iRepository.getServices().asLiveData()
    fun searchService(idSearch: String) = iRepository.getSearchService(idSearch).asLiveData()
    fun delete(id: String) = iRepository.deleteService(id).asLiveData()
    fun create(serviceCreateRequest: ServiceCreateRequest) = iRepository.createService(serviceCreateRequest).asLiveData()
    fun update(id: String, serviceUpdateRequest: ServiceUpdateRequest) = iRepository.updateService(id, serviceUpdateRequest).asLiveData()
    val user = iRepository.user().asLiveData()
    val getCustomer = iRepository.getPelanggan().asLiveData()
    val getBiaya = iRepository.getBiayaTambahan().asLiveData()

    init {
        when(val customer = getCustomer.value){
            is Resource.Success -> {
                customer.data?.forEach {
                    listCustomer[it.namaPelanggan] = it.idPelanggan
                }
            }
        }
    }
}