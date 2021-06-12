package com.example.instagram.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.instagram.R
import com.example.instagram.data.model.User
import com.example.instagram.data.repository.UserRepository
import com.example.instagram.utils.common.Event
import com.example.instagram.utils.common.Resource
import com.example.instagram.utils.network.NetworkHelper
import com.example.instagram.utils.rx.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var loggingInObserver: Observer<Boolean>

    @Mock
    private lateinit var launchDummyObserver: Observer<Event<Map<String, String>>>

    @Mock
    private lateinit var messageStingIdObserver: Observer<Resource<Int>>

    private lateinit var testScheduler: TestScheduler

    private lateinit var loginVideModel: LoginViewModel

    @Before
    fun setUp() {
        val compositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        loginVideModel = LoginViewModel(
            testSchedulerProvider,
            compositeDisposable,
            networkHelper,
            userRepository
        )
        loginVideModel.loggingIn.observeForever(loggingInObserver)
        loginVideModel.launchDummy.observeForever(launchDummyObserver)
        loginVideModel.messageStringId.observeForever(messageStingIdObserver)
    }

    @Test
    fun givenServerResponse200_whenLogin_shouldLaunchDummyActivity() {
        val email = "test@gmail.com"
        val password = "password"
        val user = User("id", "test", email, "accessToken")
        loginVideModel.emailField.value = email
        loginVideModel.passwordField.value = password
        doReturn(true)
            .`when`(networkHelper)
            .isNetworkConnected()
        doReturn(Single.just(user))
            .`when`(userRepository)
            .doUserLogin(email, password)
        loginVideModel.onLogin()
        testScheduler.triggerActions()
        verify(userRepository).saveCurrentUser(user)
        assert(loginVideModel.loggingIn.value == false)
        assert(loginVideModel.launchDummy.value == Event(hashMapOf<String, String>()))
        verify(loggingInObserver).onChanged(true)
        verify(loggingInObserver).onChanged(false)
        verify(launchDummyObserver).onChanged(Event(hashMapOf()))
    }

    @Test
    fun givenNoInternet_whenLogin_shouldShowNetworkError() {
        val email = "test@gmail.com"
        val password = "password"
        loginVideModel.emailField.value = email
        loginVideModel.passwordField.value = password
        doReturn(false)
            .`when`(networkHelper)
            .isNetworkConnected()
        loginVideModel.onLogin()
        assert(loginVideModel.messageStringId.value == Resource.error(R.string.network_connection_error))
        verify(messageStingIdObserver).onChanged(Resource.error(R.string.network_connection_error))
    }

    @After
    fun tearDown() {
        loginVideModel.loggingIn.removeObserver(loggingInObserver)
        loginVideModel.launchDummy.removeObserver(launchDummyObserver)
        loginVideModel.messageStringId.removeObserver(messageStingIdObserver)
    }
}