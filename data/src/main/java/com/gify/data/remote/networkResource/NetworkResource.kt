package com.gify.data.remote.networkResource

sealed class NetworkResource<T>(val data: T? = null,
                                val message: String? = null) {

    class Success<T>(data: T? =null): NetworkResource<T>(data)
    class Loading<T>(data: T? = null): NetworkResource<T>(data)
    class Error<T>(message: String?, data: T?=null): NetworkResource<T>(data,message)
}