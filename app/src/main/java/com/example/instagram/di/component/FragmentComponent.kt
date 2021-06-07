package com.example.instagram.di.component

import com.example.bootcamp.instagram.di.FragmentScope
import com.example.bootcamp.instagram.di.module.FragmentModule
import com.example.bootcamp.instagram.ui.dummies.DummiesFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

    fun inject(fragment: DummiesFragment)
}