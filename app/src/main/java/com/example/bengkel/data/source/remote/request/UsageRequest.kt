package com.example.bengkel.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class UsageRequest(
    @SerializedName("id_suku_cadang") val idSukuCadang : String,
    @SerializedName("jumlah_suku_cadang") val jumlahSukuCadang : String,
)
