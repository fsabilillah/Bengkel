package com.example.bengkel.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class UserResponse(

	@field:SerializedName("data")
	val data: List<DataUser>,

	@field:SerializedName("status")
	val status: Boolean
)

@Parcelize
data class DataUser(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("no_hp")
	val noHp: String,

	@field:SerializedName("hak_akses")
	val hakAkses: String,

	@field:SerializedName("id_teknisi")
	val idUsers: String,

	@field:SerializedName("gambar")
	val gambar: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("alamat")
	val alamat: String,

	@field:SerializedName("username")
	val username: String
): Parcelable
