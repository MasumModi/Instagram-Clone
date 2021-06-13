package com.example.instagram.ui.photo

import android.os.Bundle
import android.view.View
import com.example.instagram.R
import com.example.instagram.di.component.FragmentComponent
import com.example.instagram.ui.base.BaseFragment

class PhotoFragment : BaseFragment<PhotoViewModel>() {

    override fun provideLayoutId(): Int = R.layout.fragment_photo

    override fun injectDependencies(fragmentComponent: FragmentComponent) =
        fragmentComponent.inject(this)

    override fun setupView(view: View) {

    }

    override fun setupObservers() {
        super.setupObservers()

    }

    companion object {

        const val TAG = "PhotoFragment: "

        fun newInstance(): PhotoFragment {
            val arg = Bundle()
            val fragment = PhotoFragment()
            fragment.arguments = arg
            return fragment
        }

    }

}