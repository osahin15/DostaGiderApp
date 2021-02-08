package com.hawksappstudio.dostagiderapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.hawksappstudio.dostagiderapp.model.ListModel.ListEntity
import com.hawksappstudio.dostagiderapp.repo.CarDataSource
import com.hawksappstudio.dostagiderapp.repo.CarDataSourceFactory
import com.hawksappstudio.dostagiderapp.repo.FilterCarDataSourceFactory
import com.hawksappstudio.dostagiderapp.service.ArabamApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class ListViewModel : ViewModel() {

    var carList  =  MutableLiveData<PagedList<ListEntity>>()
    var loadingList = MutableLiveData<Boolean>()
    var errorList = MutableLiveData<Boolean>()

    private val compositeDisposable = CompositeDisposable()

   private val pageSize = 20

    private lateinit var sourceFactory : CarDataSourceFactory
    private lateinit var filterSourceFactory : FilterCarDataSourceFactory


    fun loadList(sort:Int,sortDirection:Int){

        sourceFactory = CarDataSourceFactory(compositeDisposable, ArabamApi.getService(),sort = sort,sortDirection = sortDirection)

        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setEnablePlaceholders(false)
            .build()

        val eventPagedList  = RxPagedListBuilder(sourceFactory,config)
            .setFetchScheduler(Schedulers.io())
            .buildObservable()
            .cache()


        compositeDisposable.add(eventPagedList.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe { loadingList.value = true }
            .subscribe({
             if (it.isNotEmpty()){
                 carList.value = it
                 loadingList.value = false
                 errorList.value = false
             }
            }, {
                errorList.value = true
                loadingList.value = false
            })
        )
    }

    fun filterList(minYear:Int,maxYear:Int){

        filterSourceFactory = FilterCarDataSourceFactory(compositeDisposable, ArabamApi.getService(),minYear,maxYear)

        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setEnablePlaceholders(false)
                .build()

        val eventPagedList  = RxPagedListBuilder(filterSourceFactory,config)
                .setFetchScheduler(Schedulers.io())
                .buildObservable()
                .cache()


        compositeDisposable.add(eventPagedList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe { loadingList.value = true }
                .subscribe({
                    if (it.isNotEmpty()){
                        carList.value = it
                        loadingList.value = false
                        errorList.value = false
                    }
                }, {
                    errorList.value = true
                    loadingList.value = false
                })
        )
    }


    fun clearCar(){
        carList.value = null
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}


