package com.example.bengkel.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class SukuCadangRequest(
    @SerializedName("nama_suku_cadang") val namaSukuCadang : String,
    @SerializedName("jumlah_stok") val jumlahStok : Int,
    @SerializedName("harga") val harga : Double,
)
