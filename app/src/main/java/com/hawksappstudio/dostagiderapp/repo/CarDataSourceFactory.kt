package com.hawksappstudio.dostagiderapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.hawksappstudio.dostagiderapp.model.ListModel.ListEntity
import com.hawksappstudio.dostagiderapp.service.ArabamApi
import io.reactivex.disposables.CompositeDisposable


class CarDataSourceFactory(private val disposable: CompositeDisposable,
                           private val arabamService : ArabamApi,
                           private val sort:Int,
                           private val sortDirection:Int
                           ) : DataSource.Factory<Int, ListEntity>() {


    private val carLiveDataSource = MutableLiveData<CarDataSource>()
    override fun create(): DataSource<Int, ListEntity> {
        val cardataSource = CarDataSource(arabamService = arabamService,disposable = disposable,sort = sort,sortDirection = sortDirection)

        carLiveDataSource.postValue(cardataSource)
        return cardataSource
    }
}