package com.example.exercise_kitten.Service

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://eu-west-1.aws.data.mongodb-api.com/app/chatreward-kytrn/endpoint/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

}