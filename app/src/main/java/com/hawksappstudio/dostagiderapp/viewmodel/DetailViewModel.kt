package com.hawksappstudio.dostagiderapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hawksappstudio.dostagiderapp.model.DetailModel
import com.hawksappstudio.dostagiderapp.model.DetailModel.DetailEntity
import com.hawksappstudio.dostagiderapp.service.ArabamApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel: ViewModel() {

    private val detailApi  = ArabamApi

    private val disposable = CompositeDisposable()

    var detailLiveData = MutableLiveData<DetailEntity>()
    var detailLoading = MutableLiveData<Boolean>()
    var detailError = MutableLiveData<Boolean>()

    fun loadDetail(carId:Int){

        disposable.add(detailApi.getService().getDetail(carId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe { detailLoading.value = true }
                .subscribe({
                    if (it != null) {
                        detailLiveData.value = it
                    }
                }, {
                    Log.d("loadDetail", "loadDetail: Failed",it)
                    detailError.value = true
                }))

    }
}