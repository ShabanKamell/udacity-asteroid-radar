package com.udacity.asteroidradar.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asteroids")
data class AsteroidEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: String,
    val estimatedDiameter: String,
    val relativeVelocity: String,
    val distanceFromEarth: String,
    val isPotentiallyHazardous: Boolean
)