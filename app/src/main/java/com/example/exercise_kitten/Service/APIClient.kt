package com.example.exercise_kitten.Service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InitService {
    fun init() {
        Retrofit.Builder()
            .baseUrl("https://eu-west-1.aws.data.mongodb-api.com/app/chatreward-kytrn/endpoint/hello")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(APIInterface::class.java)
    }
}