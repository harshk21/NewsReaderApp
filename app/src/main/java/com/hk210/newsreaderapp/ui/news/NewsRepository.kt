package com.hk210.newsreaderapp.ui.news

import android.content.Context
import com.google.gson.Gson
import com.hk210.newsreaderapp.BuildConfig
import com.hk210.newsreaderapp.R
import com.hk210.newsreaderapp.model.NewsModel
import com.hk210.newsreaderapp.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URI
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val context: Context, private val gson: Gson) {

    fun fetchNews() = flow<NetworkResult<NewsModel>> {
        val urlConnection = getUrlConnection()
        urlConnection.requestMethod = "GET"
        val responseCode = urlConnection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            var responseLine: String?
            val response = StringBuilder()

            while (reader.readLine().also { responseLine = it } != null) {
                response.append(responseLine?.trim())
            }

            val newsModel = gson.fromJson(response.toString(), NewsModel::class.java)
            emit(NetworkResult.Success(newsModel))
            reader.close()
        }

        urlConnection.disconnect()
    }.onStart {
        emit(NetworkResult.Loading())
    }.flowOn(Dispatchers.IO)
        .catch { throwable ->
            val message = when (throwable) {
                is UnknownHostException -> {
                    context.getString(R.string.no_internet_connection_message)
                }

                is SocketTimeoutException -> {
                    context.getString(R.string.connection_timeout_message)
                }

                else -> {
                    context.getString(R.string.generic_failure_message)
                }
            }
            emit(NetworkResult.Error(message))
        }

    private fun getUrlConnection(): HttpURLConnection {
        val urlConnection =
            URI.create(BuildConfig.BASE_URL).toURL().openConnection() as HttpURLConnection
        urlConnection.connectTimeout = 5000
        urlConnection.readTimeout = 5000
        return urlConnection
    }
}