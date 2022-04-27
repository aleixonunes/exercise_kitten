package com.example.exercise_kitten.data

import com.example.exercise_kitten.model.ResponseData
import com.example.exercise_kitten.data.remote.RemoteDataSource
import com.example.exercise_kitten.model.BaseApiResponse
import com.example.exercise_kitten.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun getData(): Flow<NetworkResult<ResponseData>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getData() })
        }.flowOn(Dispatchers.IO)
    }

}