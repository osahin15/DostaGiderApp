package com.hawksappstudio.dostagiderapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName






data class PropertiesEntity(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("value")
    val value: String
)

data class LocationEntity(
    @Expose
    @SerializedName("cityName")
    val cityName: String,
    @Expose
    @SerializedName("townName")
    val townName: String
)

data class CategoryEntity(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String
)