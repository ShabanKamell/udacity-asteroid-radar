package com.udacity.asteroidradar.shared.model

import android.os.Parcelable
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
) : Parcelable