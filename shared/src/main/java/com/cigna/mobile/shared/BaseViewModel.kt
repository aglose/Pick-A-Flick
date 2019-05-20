package com.cigna.mobile.shared

import androidx.lifecycle.*
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val loadingLiveData = MutableLiveData<Boolean>()
    private val errorLiveData= MutableLiveData<Int>()

    fun <T: Any> LiveData<Result<T>>.callRepo(repoCall: suspend () -> Unit): LiveData<T> {
        loadingLiveData.value = true
        viewModelScope.launch {
            repoCall.invoke()
            loadingLiveData.value = false
        }
        return Transformations.map(this) {
            when(it){
                is Result.Success -> it.data
                is Result.Error -> {
                    errorLiveData.value = it.code
                    null
                }
            }
        }
    }

    fun subscribeToErrors() : LiveData<Int> {
        return errorLiveData
    }

    fun subscribeToLoading() : LiveData<Boolean> {
        return loadingLiveData
    }
}