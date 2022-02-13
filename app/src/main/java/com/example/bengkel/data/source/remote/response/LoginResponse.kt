package com.example.bengkel.data.source.remote.response

import com.example.bengkel.data.source.local.entity.UserEntity
import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: DataLogin,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class DataLogin(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("no_hp")
	val noHp: String,

	@field:SerializedName("hak_akses")
	val hakAkses: String,

	@field:SerializedName("id_teknisi")
	val idTeknisi: String,

	@field:SerializedName("gambar")
	val gambar: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("alamat")
	val alamat: String,

	@field:SerializedName("username")
	val username: String
)


fun DataLogin.mapToEntity() : UserEntity = UserEntity(
	idTeknisi.toInt(), nama, username, password, email, alamat, noHp, gambar, hakAkses
)
