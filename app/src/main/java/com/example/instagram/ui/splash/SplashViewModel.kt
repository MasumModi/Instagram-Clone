package com.example.instagram.ui.splash

import androidx.lifecycle.MutableLiveData
import com.example.bootcamp.instagram.data.repository.UserRepository
import com.example.bootcamp.instagram.ui.base.BaseViewModel
import com.example.bootcamp.instagram.utils.common.Event
import com.example.bootcamp.instagram.utils.network.NetworkHelper
import com.example.bootcamp.instagram.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class SplashViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    // Event is used by the view model to tell the activity to launch another Activity
    // view model also provided the Bundle in the event that is needed for the Activity
    val launchDummy: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {
        // Empty Bundle passed to Activity in Event that is needed by the other Activity
        // Here in actual application we will decide which screen to open based on
        // either the user is logged in or not
        launchDummy.postValue(Event(emptyMap()))
    }
}