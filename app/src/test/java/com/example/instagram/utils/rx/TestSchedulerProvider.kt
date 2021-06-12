package com.example.instagram.utils.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

//Return same scheduler for io and ui processes
class TestSchedulerProvider(
    private val testScheduler: TestScheduler
) : SchedulerProvider{

    override fun computation(): Scheduler = testScheduler

    override fun io(): Scheduler = testScheduler

    override fun ui(): Scheduler = testScheduler

}