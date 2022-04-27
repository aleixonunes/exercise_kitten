package com.example.exercise_kitten.model

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("success")
    val success: String,
    @SerializedName("data")
    val data: DataKK
)