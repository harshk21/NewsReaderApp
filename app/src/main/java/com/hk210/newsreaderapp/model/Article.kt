package com.hk210.newsreaderapp.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Article(

    var author: String?,

    var content: String?,

    var description: String?,

    var publishedAt: String?,

    var source: Source?,

    var title: String?,

    var url: String?,

    var urlToImage: String?
) : Parcelable
