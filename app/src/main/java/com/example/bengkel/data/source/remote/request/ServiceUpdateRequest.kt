package com.example.bengkel.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class ServiceUpdateRequest (
    @SerializedName("id_users") val idUser : String,
    @SerializedName("nama_pelanggan") val namaPelanggan : String,
    @SerializedName("nama_barang") val namaBarang : String,
    @SerializedName("no_telepon") val noTelepon : String,
    @SerializedName("tanggal_service") val tanggalService : String,
    @SerializedName("tanggal_selesai") val tanggalSelesai : String,
    @SerializedName("harga_jasa") val hargaJasa : String,
    @SerializedName("keterangan") val keterangan : String,
    @SerializedName("biaya_tambahan") val biaya_tambahan : String,
    @SerializedName("status") val status : String,
)