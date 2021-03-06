package com.example.bengkel.data.source.remote

import android.util.Log
import com.example.bengkel.data.source.remote.network.ApiResponse
import com.example.bengkel.data.source.remote.network.ApiServices
import com.example.bengkel.data.source.remote.request.*
import com.example.bengkel.data.source.remote.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.http.*

class RemoteDataSource(private val apiServices: ApiServices) {
    suspend fun health() : Flow<String> {
        return flow {
            try {
                val response = apiServices.health()
                if (response.status)
                    emit(response.message)
            } catch (e: Exception){
                Log.e(RemoteDataSource::class.java.simpleName, "health: ", e)
                emit(e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun login(username: String, password : String): Flow<ApiResponse<LoginResponse>> {
        return flow {
            try {
                val response = apiServices.login(LoginRequest(username, password))
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUser(): Flow<ApiResponse<List<DataUser>>> {
        return flow {
            try {
                val response = apiServices.getUser()
                if (response.status)
                    emit(ApiResponse.Success(response.data))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun deleteUser(id: Int): Flow<ApiResponse<StatusResponse>> {
        return flow {
            try {
                val response = apiServices.deleteUser(id)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun updateUser(id: Int, userRequest: UserRequest): Flow<ApiResponse<StatusResponse>> {
        return flow {
            try {
                val response = apiServices.updateUser(id, userRequest)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun createUser(userRequest: UserRequest): Flow<ApiResponse<StatusResponse>> {
        return flow {
            try {
                val response = apiServices.createUser(userRequest)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSukuCadang(): Flow<ApiResponse<List<DataSukuCadang>>> =
        flow {
            try {
                val response = apiServices.getSukuCadang()
                if (response.status)
                    emit(ApiResponse.Success(response.data))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun createSukuCadang(sukuCadangRequest: SukuCadangRequest) =
        flow {
            try {
                val response = apiServices.createSukuCadang(sukuCadangRequest)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun updateSukuCadang(id: Int, updateSukuCadangRequest: UpdateSukuCadangRequest) =
        flow {
            try {
                val response = apiServices.updateSukuCadang(id, updateSukuCadangRequest)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun deleteSukuCadang(id: Int) =
        flow {
            try {
                val response = apiServices.deleteSukuCadang(id)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun createService(request: ServiceCreateRequest) =
        flow {
            try {
                val response = apiServices.createService(request)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            }catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun updateService(id: String, request: ServiceUpdateRequest) =
        flow {
            try {
                val response = apiServices.updateService(id, request)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            }catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getServices() =
        flow {
            try {
                val response = apiServices.getServices()
                if (response.status)
                    emit(ApiResponse.Success(response.data))
                else
                    emit(ApiResponse.Empty)
            }catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun deleteService(id: String) =
        flow {
            try {
                val response = apiServices.deleteService(id)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            }catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getPemakaian(id: String) =
        flow{
            try {
                val response = apiServices.getPemakaian(id)
                if (response.status)
                    emit(ApiResponse.Success(response.data))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)


    suspend fun createPemakaian(id: String, idSukuCadang: String, jumlahSukuCadang: String) =
        flow{
            try {
                val usage = UsageRequest(idSukuCadang, jumlahSukuCadang)
                val response = apiServices.createPemakaian(id, usage)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun updatePemakaian(idService: String, idPakai: String,  idSukuCadang: String, jumlahSukuCadang: String) =
        flow{
            try {
                val usage = UsageRequest(idSukuCadang, jumlahSukuCadang)
                val response = apiServices.updatePemakaian(idService, idPakai, usage)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)


    suspend fun deletePemakaian(id: String, idPakai: String) =
        flow{
            try {
                val response = apiServices.deletePemakaian(id, idPakai)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getServiceDone() =
        flow{
            try {
                val response = apiServices.getServiceDone()
                if (response.status)
                    emit(ApiResponse.Success(response.data))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getSearchService(idService: String) =
        flow{
            try {
                val response = apiServices.getSearchService(idService)
                if (response.status)
                    emit(ApiResponse.Success(response.data))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getPelanggan() =
        flow{
            try {
                val response = apiServices.getPelanggan()
                if (response.status)
                    emit(ApiResponse.Success(response.data))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun createPelanggan(nama: String, noHp: String, alamat: String) =
        flow{
            try {
                val customer = PelangganRequest(nama, noHp, alamat)
                val response = apiServices.createPelanggan(customer)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun updatePelanggan(id: String, nama: String, noHp: String, alamat: String) =
        flow{
            try {
                val customer = PelangganRequest(nama, noHp, alamat)
                val response = apiServices.updatePelanggan(id, customer)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun deletePelanggan(id: String) =
        flow{
            try {
                val response = apiServices.deletePelanggan(id)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)


    suspend fun getBiayaTambahan() =
        flow{
            try {
                val response = apiServices.getBiayaTambahan()
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun createBiayaTambahan(biayaTambahan: String) =
        flow{
            try {
                val response = apiServices.createBiayaTambahan(biayaTambahan)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun updateBiayaTambahan(id: String, biayaTambahan: String) =
        flow{
            try {
                val response = apiServices.updateBiayaTambahan(id, biayaTambahan)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun deleteBiayaTambahan(id: String) =
        flow{
            try {
                val response = apiServices.deleteBiayaTambahan(id)
                if (response.status)
                    emit(ApiResponse.Success(response))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
}