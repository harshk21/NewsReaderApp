package com.hk210.newsreaderapp.utils

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

fun AppCompatImageView.loadImage(context: Context, url: String) {

    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    Glide
        .with(context)
        .load(url)
        .centerCrop()
        .error(android.R.drawable.stat_notify_error)
        .placeholder(circularProgressDrawable)
        .into(this)
}