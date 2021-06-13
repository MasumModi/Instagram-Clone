package com.example.instagram

import android.content.Context
import com.example.instagram.di.component.DaggerTestComponent
import com.example.instagram.di.component.TestComponent
import com.example.instagram.di.module.ApplicationTestModule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestComponentRule(
    private val context: Context
) : TestRule {

    private var testComponent: TestComponent? = null

    fun getContext() = context

    private fun setUpDaggerTestComponent() {
        val application = context.applicationContext as InstagramApplication
        testComponent = DaggerTestComponent.builder()
            .applicationTestModule(ApplicationTestModule(application))
            .build()
        //application.setComponent(testComponent!!)
    }

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                try {
                    setUpDaggerTestComponent()
                    base?.evaluate()
                }finally {
                    testComponent = null
                }
            }
        }
    }
}