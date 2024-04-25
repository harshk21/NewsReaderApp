package com.hk210.newsreaderapp.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Source(

    var id: String?,

    var name: String?
) : Parcelable
