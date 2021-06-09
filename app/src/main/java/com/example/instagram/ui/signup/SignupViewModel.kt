package com.example.instagram.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.instagram.data.repository.UserRepository
import com.example.instagram.ui.base.BaseViewModel
import com.example.instagram.utils.common.*
import com.example.instagram.utils.network.NetworkHelper
import com.example.instagram.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class SignupViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    private val validationsList: MutableLiveData<List<Validation>> = MutableLiveData()
    val launchDummy: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    val fullNameField: MutableLiveData<String> = MutableLiveData()
    val emailField: MutableLiveData<String> = MutableLiveData()
    val passwordField: MutableLiveData<String> = MutableLiveData()
    val registeringIn: MutableLiveData<Boolean> = MutableLiveData()

    val fullNameValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.FULLNAME)
    val emailValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.EMAIL)
    val passwordValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.PASSWORD)

    private fun filterValidation(field: Validation.Field) =
        Transformations.map(validationsList) {
            it.find { validation -> validation.field == field }
                ?.run { return@run this.resource }
                ?: Resource.unknown()
        }

    override fun onCreate() {

    }

    fun onFullNameChange(fullName: String) = fullNameField.postValue(fullName)

    fun onEmailChange(email: String) = emailField.postValue(email)

    fun onPasswordChange(email: String) = passwordField.postValue(email)

    fun onSignup() {
        val fullName = fullNameField.value
        val email = emailField.value
        val password = passwordField.value

        val validations = Validator.validateSignupFields(fullName, email, password)
        validationsList.postValue(validations)

        if (validations.isNotEmpty() && fullName != null && email != null && password != null) {
            val successValidation = validations.filter { it.resource.status == Status.SUCCESS }
            if (successValidation.size == validations.size && checkInternetConnectionWithMessage()) {
                registeringIn.postValue(true)
                compositeDisposable.addAll(
                    userRepository.doUserSignup(fullName, email, password)
                        .subscribeOn(schedulerProvider.io())
                        .subscribe(
                            {
                                userRepository.saveCurrentUser(it)
                                registeringIn.postValue(false)
                                launchDummy.postValue(Event(emptyMap()))
                            },
                            {
                                handleNetworkError(it)
                                registeringIn.postValue(false)
                            }
                        )
                )
            }
        }
    }

}