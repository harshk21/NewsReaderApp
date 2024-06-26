package com.hk210.newsreaderapp.utils.network

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<Status>
}

enum class Status {
    Available, Unavailable, Losing, Lost
}
