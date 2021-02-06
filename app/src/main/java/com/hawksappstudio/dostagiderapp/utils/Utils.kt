package com.hawksappstudio.dostagiderapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.hawksappstudio.dostagiderapp.R
import kotlinx.android.synthetic.main.item_photo.view.*
import java.lang.Exception

private val shimmer = Shimmer.AlphaHighlightBuilder()
    .setDuration(1800)
    .setBaseAlpha(0.7f)
    .setHighlightAlpha(0.6f)
    .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
    .setAutoStart(true)
    .build()

private val shimmerDrawable = ShimmerDrawable().apply {
    setShimmer(shimmer)
}

fun shimmerPhoto():ShimmerDrawable{
    return shimmerDrawable
}


fun ImageView.downloadListUrl(imageUrl:String?){
        val url = imageUrl?.replace("{0}","120x90")
    Glide.with(context)
        .load(url)
        .placeholder(shimmerDrawable)
        .error(shimmerDrawable                         )
        .into(this)

}


@BindingAdapter("android:downloadListUrl")
fun downloadImage(view:ImageView,url:String?){
    view.downloadListUrl(url)
}


fun ImageView.downloadBigUrl(imageUrl: String?){
    val url = imageUrl?.replace("{0}","800x600")
    Glide.with(context)
        .load(url)
        .placeholder(shimmerDrawable)
        .error(shimmerDrawable)
        .into(this)

}

@BindingAdapter("android:downloadBigPhoto")
fun downloadBigImage(view: ImageView,url: String?){
        view.downloadBigUrl(url)
}

