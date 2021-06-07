package com.example.instagram.di.component

import com.example.bootcamp.instagram.di.ViewModelScope
import com.example.bootcamp.instagram.di.module.ViewHolderModule
import com.example.bootcamp.instagram.ui.dummies.DummyItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: DummyItemViewHolder)
}