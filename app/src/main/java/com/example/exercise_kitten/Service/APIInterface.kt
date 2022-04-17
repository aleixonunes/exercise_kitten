package com.example.exercise_kitten.Service

import io.reactivex.Observable
import retrofit2.http.GET

interface APIInterface {

    @GET("hello")
    fun getData(): Observable<ResponseData>
}