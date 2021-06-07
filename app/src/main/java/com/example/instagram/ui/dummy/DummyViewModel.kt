package com.example.instagram.ui.dummy

import com.example.bootcamp.instagram.data.repository.DummyRepository
import com.example.bootcamp.instagram.ui.base.BaseViewModel
import com.example.bootcamp.instagram.utils.network.NetworkHelper
import com.example.bootcamp.instagram.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class DummyViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val dummyRepository: DummyRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    override fun onCreate() {
        // do something
    }
}