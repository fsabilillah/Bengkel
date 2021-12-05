package com.example.bengkel.data

import com.example.bengkel.data.source.local.entity.UserEntity
import com.example.bengkel.data.source.remote.request.*
import com.example.bengkel.data.source.remote.response.*
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

interface IRepository {
    fun health(): Flow<String>
    fun login(username :String, password: String) : Flow<Resource<DataLogin>>
    fun isLogin() : Flow<Int>
    fun user(): Flow<UserEntity>
    suspend fun logout()

    /** user **/
    fun getUser(): Flow<Resource<List<DataUser>>>
    fun deleteUser(id: Int): Flow<Resource<StatusResponse>>
    fun updateUser(id: Int, userRequest: UserRequest): Flow<Resource<StatusResponse>>
    fun createUser(userRequest: UserRequest): Flow<Resource<StatusResponse>>

    /** suku cadang **/
    fun getSukuCadang(): Flow<Resource<List<DataSukuCadang>>>
    fun createSukuCadang(sukuCadangRequest: SukuCadangRequest): Flow<Resource<StatusResponse>>
    fun updateSukuCadang(id: Int, updateSukuCadangRequest: UpdateSukuCadangRequest): Flow<Resource<StatusResponse>>
    fun deleteSukuCadang(id: Int): Flow<Resource<StatusResponse>>

    /** service **/
    fun createService(request: ServiceCreateRequest): Flow<Resource<StatusResponse>>
    fun updateService(id: String, request: ServiceUpdateRequest): Flow<Resource<StatusResponse>>
    fun getServices() : Flow<Resource<List<DataService>>>
    fun deleteService(id: String): Flow<Resource<StatusResponse>>

    /** pemakaian **/
    fun createPemakaian(id: String, idSukuCadang: String, jumlahSukuCadang: String): Flow<Resource<StatusResponse>>
    fun updatePemakaian(idService: String, idPakai: String,  idSukuCadang: String, jumlahSukuCadang: String): Flow<Resource<StatusResponse>>
    fun getPemakaian(id: String): Flow<Resource<List<DataUsage>>>
    fun deletePemakaian(id: String, idPakai: String): Flow<Resource<StatusResponse>>
}