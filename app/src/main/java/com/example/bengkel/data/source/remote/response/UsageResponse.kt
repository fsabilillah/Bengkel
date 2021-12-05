package com.example.bengkel.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsageResponse(

	@field:SerializedName("data")
	val data: List<DataUsage>,

	@field:SerializedName("status")
	val status: Boolean
) : Parcelable

@Parcelize
data class DataUsage(

	@field:SerializedName("id_suku_cadang")
	val idSukuCadang: String,

	@field:SerializedName("id_pemakaian")
	val idPemakaian: String,

	@field:SerializedName("jumlah_suku_cadang")
	val jumlahSukuCadang: String,

	@field:SerializedName("total_beli")
	val totalBeli: String,

	@field:SerializedName("nama_suku_cadang")
	val namaSukuCadang: String
) : Parcelable
