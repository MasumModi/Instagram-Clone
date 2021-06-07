package com.example.instagram.di.module

import androidx.lifecycle.LifecycleRegistry
import com.example.bootcamp.instagram.di.ViewModelScope
import com.example.bootcamp.instagram.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}