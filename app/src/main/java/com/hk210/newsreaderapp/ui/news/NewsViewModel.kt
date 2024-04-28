package com.hk210.newsreaderapp.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hk210.newsreaderapp.model.NewsModel
import com.hk210.newsreaderapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    init {
        fetchNews()
    }

    val newsResponse = MutableLiveData<NetworkResult<NewsModel>>()

    fun fetchNews() {
        viewModelScope.launch {
            newsRepository.fetchNews().distinctUntilChanged().flowOn(Dispatchers.IO)
                .collect {
                    newsResponse.value = it
                }
        }
    }

    fun sortNews(filter: String) {
        viewModelScope.launch {
            newsRepository.sortNews(newsResponse.value?.data, filter).distinctUntilChanged().flowOn(Dispatchers.IO)
                .collect {
                    newsResponse.value = it
                }
        }
    }
}
