package com.hawksappstudio.dostagiderapp.utils


import android.widget.ImageView
import androidx.databinding.BindingAdapter


import com.bumptech.glide.Glide

import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable


const val YEAR = 2
const val DATE = 1
const val PRICE = 0
const val ASCENDING = 0
const val DESCENDING = 1

private val shimmer = Shimmer.AlphaHighlightBuilder()
    .setDuration(1800)
    .setBaseAlpha(0.7f)
    .setHighlightAlpha(0.6f)
    .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
    .setAutoStart(true)
    .build()

private val shimmerDrawable = ShimmerDrawable().apply {
    this.setShimmer(shimmer)
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


fun ImageView.downloadBigDetailUrl(imageUrl: String?){
    val url = imageUrl?.replace("{0}","1920x1080")

    Glide.with(context)
        .load(url)
        .placeholder(shimmerDrawable)
        .error(shimmerDrawable)
        .into(this)

}

@BindingAdapter("android:downloadBigDetailPhoto")
fun downloadBigDetailImage(view: ImageView,url: String?){
    view.downloadBigDetailUrl(url)
}
