package com.hawksappstudio.dostagiderapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListModel {
    data class ListEntity(
        @Expose
        @SerializedName("id")
        val id: Int,
        @Expose
        @SerializedName("title")
        val title: String,
        @Expose
        @SerializedName("location")
        val location: LocationEntity,
        @Expose
        @SerializedName("category")
        val category: CategoryEntity,
        @Expose
        @SerializedName("modelName")
        val modelName: String,
        @Expose
        @SerializedName("price")
        val price: Int,
        @Expose
        @SerializedName("priceFormatted")
        val priceFormatted: String,
        @Expose
        @SerializedName("date")
        val date: String,
        @Expose
        @SerializedName("dateFormatted")
        val dateFormatted: String,
        @Expose
        @SerializedName("photo")
        val photo:String,
        @Expose
        @SerializedName("properties")
        val properties: List<PropertiesEntity>
    )
}