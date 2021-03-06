package com.example.bengkel.data.source.remote.network

import com.example.bengkel.data.source.remote.request.*
import com.example.bengkel.data.source.remote.response.*
import retrofit2.http.*

interface ApiServices {

    @GET("health")
    suspend fun health(): StatusResponse

    /** users **/
    @POST("login")
    suspend fun login(@Body request: LoginRequest) : LoginResponse

    @POST("users")
    suspend fun createUser(@Body request: UserRequest): StatusResponse

    @GET("users")
    suspend fun getUser(): UserResponse

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int) : StatusResponse

    @POST("update_users/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body request: UserRequest): StatusResponse


    /** suku cadang **/
    @GET("suku_cadang")
    suspend fun getSukuCadang(): SukuCadangResponse

    @POST("suku_cadang")
    suspend fun createSukuCadang(@Body request: SukuCadangRequest): StatusResponse

    @POST("update_suku_cadang/{id}")
    suspend fun updateSukuCadang(@Path("id") id: Int, @Body request: UpdateSukuCadangRequest): StatusResponse

    @DELETE("suku_cadang/{id}")
    suspend fun deleteSukuCadang(@Path("id") id: Int): StatusResponse

    /** service **/
    @POST("service")
    suspend fun createService(@Body request: ServiceCreateRequest): StatusResponse

    @POST("update_service/{id}")
    suspend fun updateService(@Path("id") id: String, @Body request: ServiceUpdateRequest): StatusResponse

    @GET("service")
    suspend fun getServices() : ServiceResponse

    @DELETE("service/{id}")
    suspend fun deleteService(@Path("id") id: String): StatusResponse

    @GET("service_selesai")
    suspend fun getServiceDone() : ServiceResponse

    @GET("service/{id}")
    suspend fun getSearchService(@Path("id") idSearch : String): ServiceResponse

    /** pemakaian **/
    @GET("pemakaian/{id}")
    suspend fun getPemakaian(@Path("id") id: String): UsageResponse

    @POST("pemakaian/{id}")
    suspend fun createPemakaian(@Path("id") id: String, @Body request: UsageRequest): StatusResponse

    @POST("update_pemakaian/{id_service}/{id_pakai}")
    suspend fun updatePemakaian(@Path("id_service") idService: String, @Path("id_pakai") idPakai: String, @Body request: UsageRequest): StatusResponse

    @DELETE("pemakaian/{id_service}/{id_pakai}")
    suspend fun deletePemakaian(@Path("id_service") idService: String, @Path("id_pakai") idPakai: String): StatusResponse

    /** pelanggan **/
    @GET("pelanggan")
    suspend fun getPelanggan(): PelangganResponse

    @POST("pelanggan")
    suspend fun createPelanggan(@Body request: PelangganRequest): StatusResponse

    @POST("update_pelanggan/{id}")
    suspend fun updatePelanggan(@Path("id") id: String,@Body request: PelangganRequest): StatusResponse

    @DELETE("pelanggan/{id}")
    suspend fun deletePelanggan(@Path("id") id: String): StatusResponse

    /** biaya **/

    @GET("biaya_tambahan")
    suspend fun getBiayaTambahan(): BiayaResponse

    @DELETE("biaya_tambahan/{id}")
    suspend fun deleteBiayaTambahan(@Path("id") id: String): StatusResponse

    @FormUrlEncoded
    @POST("biaya_tambahan")
    suspend fun createBiayaTambahan(@Field("biaya_tambahan") biayaTambahan: String): StatusResponse

    @FormUrlEncoded
    @POST("update_biaya_tambahan/{id}")
    suspend fun updateBiayaTambahan(@Path("id") id: String, @Field("biaya_tambahan") biayaTambahan: String): StatusResponse
}