package com.hk210.newsreaderapp.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class NewsModel(

    var articles: List<Article?>? = null,

    var status: String? = null
) : Parcelable
