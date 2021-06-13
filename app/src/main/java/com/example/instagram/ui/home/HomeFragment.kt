package com.example.instagram.ui.home

import android.os.Bundle
import android.view.View
import com.example.instagram.R
import com.example.instagram.di.component.FragmentComponent
import com.example.instagram.ui.base.BaseFragment

class HomeFragment : BaseFragment<HomeViewModel>() {

    override fun provideLayoutId(): Int = R.layout.fragment_home

    override fun injectDependencies(fragmentComponent: FragmentComponent) =
        fragmentComponent.inject(this)


    override fun setupView(view: View) {

    }

    override fun setupObservers() {
        super.setupObservers()

    }

    companion object {

        const val TAG = "HomeFragment: "

        fun newInstance(): HomeFragment {
            val arg = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = arg
            return fragment
        }

    }

}