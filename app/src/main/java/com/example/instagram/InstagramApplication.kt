package com.example.instagram

import android.app.Application
import com.example.instagram.di.component.ApplicationComponent
import com.example.instagram.di.component.DaggerApplicationComponent
import com.example.instagram.di.module.ApplicationModule

class InstagramApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    fun setComponent(applicationComponent: ApplicationComponent){
        this.applicationComponent = applicationComponent
    }
}