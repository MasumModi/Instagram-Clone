package com.example.instagram.di.component

import com.example.bootcamp.instagram.di.ActivityScope
import com.example.bootcamp.instagram.di.module.ActivityModule
import com.example.bootcamp.instagram.ui.dummy.DummyActivity
import com.example.bootcamp.instagram.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: SplashActivity)

    fun inject(activity: DummyActivity)
}