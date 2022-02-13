package com.example.bengkel.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class ServiceCreateRequest(
    @SerializedName("id_teknisi") val idTeknisi: String,
    @SerializedName("id_pelanggan") val idPelanggan: String,
    @SerializedName("nama_barang") val namaBarang: String,
    @SerializedName("tanggal_selesai") val tanggalSelesai: String,
    @SerializedName("keterangan") val keterangan: String,
    @SerializedName("harga_jasa") val hargaJasa: String,
)