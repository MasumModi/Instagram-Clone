package com.example.instagram.ui.profile

import android.os.Bundle
import android.view.View
import com.example.instagram.R
import com.example.instagram.di.component.FragmentComponent
import com.example.instagram.ui.base.BaseFragment

class ProfileFragment : BaseFragment<ProfileViewModel>() {

    override fun provideLayoutId(): Int = R.layout.fragment_profile

    override fun injectDependencies(fragmentComponent: FragmentComponent) =
        fragmentComponent.inject(this)

    override fun setupView(view: View) {

    }

    override fun setupObservers() {
        super.setupObservers()

    }

    companion object {

        const val TAG = "ProfileFragment: "

        fun newInstance(): ProfileFragment {
            val arg = Bundle()
            val fragment = ProfileFragment()
            fragment.arguments = arg
            return fragment
        }

    }

}