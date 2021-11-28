package com.example.bengkel.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("nama") val nama : String,
    @SerializedName("username") val username : String,
    @SerializedName("password") val password : String,
    @SerializedName("email") val email : String,
    @SerializedName("alamat") val alamat : String,
    @SerializedName("no_hp") val noHp : String
)
