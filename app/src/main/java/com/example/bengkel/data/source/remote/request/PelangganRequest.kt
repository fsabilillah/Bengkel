package com.example.bengkel.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class PelangganRequest (
    @SerializedName("nama_pelanggan") val nama : String,
    @SerializedName("no_hp_pelanggan") val noHp : String,
    @SerializedName("alamat_pelanggan") val alamat : String
)

