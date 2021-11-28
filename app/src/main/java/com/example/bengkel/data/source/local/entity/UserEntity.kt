package com.example.bengkel.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_users") var id_users : Int,
    @ColumnInfo(name = "nama") var nama : String? = "",
    @ColumnInfo(name = "username") var username : String,
    @ColumnInfo(name = "password") var password : String,
    @ColumnInfo(name = "email") var email : String? = "",
    @ColumnInfo(name = "alamat") var alamat : String,
    @ColumnInfo(name = "no_hp") var no_hp : String,
    @ColumnInfo(name = "gambar") var gambar : String,
    @ColumnInfo(name = "hak_akses") var hak_akses : String
)