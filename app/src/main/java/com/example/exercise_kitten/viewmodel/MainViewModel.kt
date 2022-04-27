package com.example.exercise_kitten.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.exercise_kitten.data.Repository
import com.example.exercise_kitten.model.ResponseData
import com.example.exercise_kitten.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor
    (
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<ResponseData>> = MutableLiveData()
    val response: LiveData<NetworkResult<ResponseData>> = _response

    fun fetchDataResponse() = viewModelScope.launch {
        repository.getData().collect { values ->
            _response.value = values
        }
    }

}