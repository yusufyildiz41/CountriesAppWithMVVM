package com.yusufyildiz.kotlincountriesapp.util

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yusufyildiz.kotlincountriesapp.R

fun ImageView.downloadFromURL(url :String?,progressDrawable: CircularProgressDrawable){

    val options = RequestOptions()
        .placeholder(progressDrawable) // what we show until the image is downloaded
        .error(R.drawable.ic_launcher_foreground)


    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)


}

fun placeHolderProgressBar(context : Context) : CircularProgressDrawable
{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadUrl")  // XML
fun downloadImage(view : ImageView,url : String?)
{
    view.downloadFromURL(url, placeHolderProgressBar(view.context))
}