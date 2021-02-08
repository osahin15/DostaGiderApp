package com.hawksappstudio.dostagiderapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.hawksappstudio.dostagiderapp.model.ListModel.ListEntity
import com.hawksappstudio.dostagiderapp.service.ArabamApi
import io.reactivex.disposables.CompositeDisposable




class FilterCarDataSource(private val disposable: CompositeDisposable,
                          private val arabamService : ArabamApi,
                          private val minYear:Int,
                          private val maxYear:Int,

                          ) : PageKeyedDataSource<Int, ListEntity>() {


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ListEntity>
    ) {
        val numberOfItems = params.requestedLoadSize
        createObservable(0,1,numberOfItems,callback,null)

    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ListEntity>
    ) {

    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ListEntity>
    ) {
        val page = params.key
        val numberOfItems = params.requestedLoadSize
        createObservable(page,page+1,numberOfItems,null,callback)
    }

    private fun createObservable(requestedPage:Int,
                                 adjacentPage:Int,
                                 requestedLoadSize:Int,
                                 initialcallback: LoadInitialCallback<Int, ListEntity>?,
                                 callback: LoadCallback<Int, ListEntity>?
    ){
        disposable.add(arabamService.getFilterList(
            minYear = minYear,
            maxYear = maxYear,
            requestedLoadSize,
            requestedPage
        ).subscribe(
            { response ->
                Log.d("CDT", "Loading Page : $requestedPage")
                initialcallback?.onResult(response, null, adjacentPage)
                callback?.onResult(response, adjacentPage)

            },
            {
                Log.d("CDT", "Error Loading Page : $requestedPage",it)
            }
        ))
    }
}