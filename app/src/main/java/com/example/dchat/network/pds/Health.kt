package com.example.dchat.network.pds

import com.google.gson.annotations.SerializedName

data class Health(
    @SerializedName("version") val version: String
)
