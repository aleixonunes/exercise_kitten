package com.example.exercise_kitten.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIInterface) {
    suspend fun getData() =
        apiService.getData()
}