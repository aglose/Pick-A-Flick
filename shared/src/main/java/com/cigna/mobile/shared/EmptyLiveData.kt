package com.cigna.mobile.shared

import androidx.lifecycle.LiveData

/**
 * A LiveData class that is just returns an empty version
 *
 *
 * when you need to return an empty livedata for a db key when the resource will always be a
 * network resource
 */
class EmptyLiveData<T> private constructor() : LiveData<T>() {

    init {
        postValue(null)
    }

    companion object {

        /**
         * Create live data.
         *
         * @param <T> the type parameter
         * @return the live data
        </T> */
        fun <T> create(): LiveData<T> {

            return EmptyLiveData()
        }
    }
}