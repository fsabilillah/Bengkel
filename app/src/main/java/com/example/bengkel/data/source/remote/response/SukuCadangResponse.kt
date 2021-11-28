package com.example.bengkel.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SukuCadangResponse(

	@field:SerializedName("data")
	val data: List<DataSukuCadang>,

	@field:SerializedName("status")
	val status: Boolean
) : Parcelable

@Parcelize
data class DataSukuCadang(

	@field:SerializedName("jumlah_stok")
	val jumlahStok: String,

	@field:SerializedName("harga")
	val harga: String,

	@field:SerializedName("nama_suku_cadang")
	val namaSukuCadang: String,

	@field:SerializedName("id_suku_cadang")
	val idSukuCadang: String
) : Parcelable
