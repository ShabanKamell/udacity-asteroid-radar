package com.udacity.asteroidradar.shared.date

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

object AppThreeTenDate {

    fun setup(app: Application) {
        AndroidThreeTen.init(app)
    }
}