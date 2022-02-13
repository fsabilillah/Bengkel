package com.example.bengkel.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class BiayaResponse(

	@field:SerializedName("data")
	val data: List<DataBiaya>,

	@field:SerializedName("status")
	val status: Boolean
)

@Parcelize
data class DataBiaya(

	@field:SerializedName("id_biaya_tambahan")
	val idBiayaTambahan: String,

	@field:SerializedName("biaya_tambahan")
	val biayaTambahan: String
): Parcelable
