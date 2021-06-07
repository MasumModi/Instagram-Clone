package com.example.instagram.di.component

import com.example.instagram.di.FragmentScope
import com.example.instagram.di.module.FragmentModule
import com.example.instagram.ui.dummies.DummiesFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

    fun inject(fragment: DummiesFragment)
}