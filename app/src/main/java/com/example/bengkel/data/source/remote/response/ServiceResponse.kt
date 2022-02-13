package com.example.bengkel.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServiceResponse(

	@field:SerializedName("data")
	val data: List<DataService>,

	@field:SerializedName("status")
	val status: Boolean
) : Parcelable

@Parcelize
data class DataService(

	@field:SerializedName("id_nota")
	val idNota: String,

	@field:SerializedName("nama_pelanggan")
	val namaPelanggan: String,

	@field:SerializedName("keterangan")
	val keterangan: String,

	@field:SerializedName("tanggal_selesai")
	val tanggalSelesai: String,

	@field:SerializedName("tanggal_service")
	val tanggalService: String,

	@field:SerializedName("id_users")
	val idUsers: String,

	@field:SerializedName("nama_barang")
	val namaBarang: String,

	@field:SerializedName("total_harga")
	val totalHarga: String,

	@field:SerializedName("biaya_tambahan")
	val biayaTambahan: String,

	@field:SerializedName("id_service")
	val idService: String,

	@field:SerializedName("no_telepon")
	val noTelepon: String,

	@field:SerializedName("harga_jasa")
	val hargaJasa: String,

	@field:SerializedName("status")
	val status: String
) : Parcelable
