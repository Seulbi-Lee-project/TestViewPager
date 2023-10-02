package com.example.testviewpager.api


import com.google.gson.annotations.SerializedName

data class Maxres(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)