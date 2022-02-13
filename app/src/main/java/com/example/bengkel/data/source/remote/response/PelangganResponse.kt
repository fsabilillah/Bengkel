package com.example.bengkel.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PelangganResponse(

	@field:SerializedName("data")
	val data: List<DataPelanggan>,

	@field:SerializedName("status")
	val status: Boolean
)

@Parcelize
data class DataPelanggan(

	@field:SerializedName("no_hp_pelanggan")
	val noHpPelanggan: String,

	@field:SerializedName("nama_pelanggan")
	val namaPelanggan: String,

	@field:SerializedName("alamat_pelanggan")
	val alamatPelanggan: String,

	@field:SerializedName("id_pelanggan")
	val idPelanggan: String
): Parcelable
