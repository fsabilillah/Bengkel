package com.example.bengkel.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class StatusResponse (
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Boolean
    )