package com.udacity.asteroidradar

import android.app.Application
import com.udacity.asteroidradar.shared.date.AppThreeTenDate
import com.udacity.asteroidradar.work.AsteroidsRefreshWorker

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppThreeTenDate.setup(this)
        AsteroidsRefreshWorker.setup()
    }
}