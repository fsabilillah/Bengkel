package com.example.bengkel.data

import com.example.bengkel.data.source.local.LocalDataSource
import com.example.bengkel.data.source.remote.RemoteDataSource
import com.example.bengkel.data.source.remote.network.ApiResponse
import com.example.bengkel.data.source.remote.request.*
import com.example.bengkel.data.source.remote.response.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IRepository {

    override fun health() = flow {
        val result = remoteDataSource.health().first()
        emit(result)
    }

    override fun login(username: String, password: String): Flow<Resource<DataLogin>> {
        return flow {
            emit(Resource.Loading())
            when(val result = remoteDataSource.login(username, password).first()){
                is ApiResponse.Success -> {
                    localDataSource.insertUser(result.data.data.mapToEntity())
                    emit(Resource.Success(result.data.data))
                }
                is ApiResponse.Error -> emit(Resource.Error<DataLogin>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<DataLogin>("data empty"))
            }
        }
    }

    override fun isLogin() = localDataSource.isLogin()

    override fun user() = localDataSource.getUser()

    override suspend fun logout() {
        localDataSource.logout()
    }

    override fun getUser()=
        flow {
            emit(Resource.Loading())
            when(val result = remoteDataSource.getUser().first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<List<DataUser>>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<List<DataUser>>("data empty"))
            }
        }

    override fun deleteUser(id: Int)=
        flow {
            emit(Resource.Loading())
            when(val result = remoteDataSource.deleteUser(id).first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<StatusResponse>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<StatusResponse>("data empty"))
            }
        }

    override fun updateUser(id: Int, userRequest: UserRequest)=
        flow {
            emit(Resource.Loading())
            when(val result = remoteDataSource.updateUser(id, userRequest).first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<StatusResponse>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<StatusResponse>("data empty"))
            }
        }

    override fun createUser(userRequest: UserRequest) =
        flow {
            emit(Resource.Loading())
            when(val result = remoteDataSource.createUser(userRequest).first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<StatusResponse>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<StatusResponse>("data empty"))
            }
        }

    override fun getSukuCadang() =
        flow {
            emit(Resource.Loading())
            when(val result = remoteDataSource.getSukuCadang().first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<List<DataSukuCadang>>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<List<DataSukuCadang>>("data empty"))
            }
        }

    override fun createSukuCadang(sukuCadangRequest: SukuCadangRequest) =
        flow {
            emit(Resource.Loading())
            when(val result = remoteDataSource.createSukuCadang(sukuCadangRequest).first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<StatusResponse>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<StatusResponse>("data empty"))
            }
        }

    override fun updateSukuCadang(
        id: Int,
        updateSukuCadangRequest: UpdateSukuCadangRequest
    ) =
        flow {
            emit(Resource.Loading())
            when(val result = remoteDataSource.updateSukuCadang(id, updateSukuCadangRequest).first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<StatusResponse>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<StatusResponse>("data empty"))
            }
        }

    override fun deleteSukuCadang(id: Int)=
        flow {
            emit(Resource.Loading())
            when(val result = remoteDataSource.deleteSukuCadang(id).first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<StatusResponse>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<StatusResponse>("data empty"))
            }
        }

    override fun createService(request: ServiceCreateRequest) =
        flow {
            emit(Resource.Loading())
            when(val result = remoteDataSource.createService(request).first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<StatusResponse>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<StatusResponse>("data empty"))
            }
        }

    override fun updateService(id: String, request: ServiceUpdateRequest) =
        flow {
            emit(Resource.Loading())
            when(val result = remoteDataSource.updateService(id, request).first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<StatusResponse>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<StatusResponse>("data empty"))
            }
        }

    override fun getServices() =
        flow {
            emit(Resource.Loading())
            when(val result = remoteDataSource.getServices().first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<List<DataService>>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<List<DataService>>("data empty"))
            }
        }

    override fun deleteService(id: String) =
        flow {
            emit(Resource.Loading())
            when(val result = remoteDataSource.deleteService(id).first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<StatusResponse>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<StatusResponse>("data empty"))
            }
        }

    override fun getServiceDone() =
        flow {
            emit(Resource.Loading())
            when(val result = remoteDataSource.getServiceDone().first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<List<DataService>>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<List<DataService>>("data empty"))
            }
        }

    override fun getSearchService(idSearch: String) =
        flow {
            emit(Resource.Loading())
            when(val result = remoteDataSource.getSearchService(idSearch).first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<List<DataService>>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<List<DataService>>("data empty"))
            }
        }

    override fun createPemakaian(id: String, idSukuCadang: String, jumlahSukuCadang: String) =
        flow{
            emit(Resource.Loading())
            when(val result = remoteDataSource.createPemakaian(id, idSukuCadang, jumlahSukuCadang).first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<StatusResponse>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<StatusResponse>("data empty"))
            }
        }

    override fun updatePemakaian(idService: String, idPakai: String,  idSukuCadang: String, jumlahSukuCadang: String) =
        flow{
            emit(Resource.Loading())
            when(val result = remoteDataSource.updatePemakaian(idService, idPakai, idSukuCadang, jumlahSukuCadang).first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<StatusResponse>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<StatusResponse>("data empty"))
            }
        }

    override fun getPemakaian(id: String) =
        flow{
            emit(Resource.Loading())
            when(val result = remoteDataSource.getPemakaian(id).first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<List<DataUsage>>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<List<DataUsage>>("data empty"))
            }
        }

    override fun deletePemakaian(id: String, idPakai: String) =
        flow{
            emit(Resource.Loading())
            when(val result = remoteDataSource.deletePemakaian(id, idPakai).first()){
                is ApiResponse.Success -> emit(Resource.Success(result.data))
                is ApiResponse.Error -> emit(Resource.Error<StatusResponse>(result.errorMessage))
                is ApiResponse.Empty -> emit(Resource.Error<StatusResponse>("data empty"))
            }
        }
}