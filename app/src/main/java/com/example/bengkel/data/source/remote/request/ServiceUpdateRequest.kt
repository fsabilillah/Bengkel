package com.example.bengkel.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class ServiceUpdateRequest (
    @SerializedName("id_teknisi") val idTeknisi : String,
    @SerializedName("id_pelanggan") val idPelanggan : String,
    @SerializedName("nama_barang") val namaBarang : String,
    @SerializedName("tanggal_selesai") val tanggalSelesai : String,
    @SerializedName("harga_jasa") val hargaJasa : String,
    @SerializedName("keterangan") val keterangan : String,
    @SerializedName("id_biaya_tambahan") val idBiayaTambahan : String,
    @SerializedName("status") val status : String,
)