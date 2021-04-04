package com.example.aicvirtualtour.data

import java.lang.Exception

/**
 * Helper class for returning network calls with statuses
 */
sealed class ResponseState<out R> {

    data class Success<out T>(val data: T): ResponseState<T>()
    data class Error(val e: Exception): ResponseState<Nothing>()
    object Loading: ResponseState<Nothing>()
}