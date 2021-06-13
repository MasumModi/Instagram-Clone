package com.example.instagram.di.component

import com.example.instagram.di.ViewModelScope
import com.example.instagram.di.module.ViewHolderModule
import com.example.instagram.ui.dummies.DummyItemViewHolder
import com.example.instagram.ui.home.posts.PostItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: DummyItemViewHolder)

    fun inject(viewHolder: PostItemViewHolder)

}