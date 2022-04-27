package com.example.exercise_kitten.data.remote

import com.example.exercise_kitten.model.ResponseData
import retrofit2.Response
import retrofit2.http.GET

interface APIInterface {

    @GET("hello")
    suspend fun getData(): Response<ResponseData>
}