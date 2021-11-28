package com.example.bengkel.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class UpdateSukuCadangRequest(
    @SerializedName("nama_suku_cadang") val namaSukuCadang : String,
    @SerializedName("jumlah_stok") val jumlahStok : Int,
    @SerializedName("harga") val harga : Double,
    @SerializedName("penambahan") val penambahan : Int,
)
