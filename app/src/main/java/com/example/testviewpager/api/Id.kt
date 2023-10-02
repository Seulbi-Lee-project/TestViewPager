package com.example.testviewpager.api


import com.google.gson.annotations.SerializedName

data class Id(
    @SerializedName("channelId")
    val channelId: String,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("videoId")
    val videoId: String
)