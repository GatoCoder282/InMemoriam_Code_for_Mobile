package com.example.inmemoriam.features.githubEjemplo.presentation.error

import android.content.Context
import com.example.inmemoriam.R
import com.example.inmemoriam.features.githubEjemplo.domain.error.Failure

class ErrorMessageProvider(private val context: Context) {
    fun getMessage(failure: Failure): String {
        return when (failure) {
            is Failure.NetworkConnection -> context.getString(R.string.error_network)
            is Failure.ServerError -> context.getString(R.string.error_server)
            is Failure.NotFound -> context.getString(R.string.error_not_found)
            is Failure.EmptyBody -> context.getString(R.string.error_empty_response)
            is Failure.Unknown -> context.getString(R.string.error_unknown)
        }
    }
}