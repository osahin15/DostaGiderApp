package com.hawksappstudio.dostagiderapp.service

import com.hawksappstudio.dostagiderapp.model.DetailModel
import com.hawksappstudio.dostagiderapp.model.DetailModel.DetailEntity
import com.hawksappstudio.dostagiderapp.model.ListModel
import com.hawksappstudio.dostagiderapp.model.ListModel.ListEntity

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ArabamApi {

    @GET("api/v1/listing")
    fun getList(@Query("sort")sort:Int,@Query("sortDirection")sortDirection:Int,@Query("take")take:Int
    ,@Query("skip")skip:Int) : Single<List<ListEntity>>

    @GET("api/v1/detail")
    fun getDetail(@Query("id")id:Int) : Single<DetailEntity>



    companion object{
        const val BASE_URL = "http://sandbox.arabamd.com/"
        fun getService(): ArabamApi{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ArabamApi::class.java)
        }
    }

}