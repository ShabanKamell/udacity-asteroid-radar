package com.udacity.asteroidradar.shared.model

import android.os.Parcelable
import com.udacity.asteroidradar.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Asteroid(
    val id: String,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: String,
    val estimatedDiameter: String,
    val relativeVelocity: String,
    val distanceFromEarth: String,
    val isPotentiallyHazardous: Boolean
) : Parcelable {
    val hazardousImage: Int
        get() {
            return if (isPotentiallyHazardous) R.drawable.asteroid_hazardous else R.drawable.asteroid_safe
        }
}