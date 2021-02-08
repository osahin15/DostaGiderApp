package com.hawksappstudio.dostagiderapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.hawksappstudio.dostagiderapp.model.ListModel.ListEntity
import com.hawksappstudio.dostagiderapp.service.ArabamApi
import io.reactivex.disposables.CompositeDisposable


class FilterCarDataSourceFactory(private val disposable: CompositeDisposable,
                                 private val arabamService : ArabamApi,
                                 private val minYear:Int,
                                 private val maxYear:Int
                           ) : DataSource.Factory<Int, ListEntity>() {


    private val carLiveDataSource = MutableLiveData<FilterCarDataSource>()
    override fun create(): DataSource<Int, ListEntity> {
        val filterCardataSource = FilterCarDataSource(arabamService = arabamService,disposable = disposable,minYear = minYear,maxYear = maxYear)

        carLiveDataSource.postValue(filterCardataSource)
        return filterCardataSource
    }
}