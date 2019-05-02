package com.cigna.mobile.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    protected val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    protected val errorLiveData: MutableLiveData<Int> = MutableLiveData()

    fun subscribeToErrors() : LiveData<Int> {
        return errorLiveData
    }

    fun subscribeToLoading() : LiveData<Boolean> {
        return loadingLiveData
    }
}