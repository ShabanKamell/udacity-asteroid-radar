package com.udacity.asteroidradar.database

import com.udacity.asteroidradar.shared.model.Asteroid


object AsteroidEntityMapper {

    fun toEntity(input: Asteroid): AsteroidEntity {
        return AsteroidEntity(
            id = input.id.toLong(),
            codename = input.codename,
            closeApproachDate = input.closeApproachDate,
            absoluteMagnitude = input.absoluteMagnitude,
            estimatedDiameter = input.estimatedDiameter,
            relativeVelocity = input.relativeVelocity,
            distanceFromEarth = input.distanceFromEarth,
            isPotentiallyHazardous = input.isPotentiallyHazardous
        )
    }

    fun fromEntity(input: AsteroidEntity): Asteroid {
        return Asteroid(
            id = input.id.toString(),
            codename = input.codename,
            closeApproachDate = input.closeApproachDate,
            absoluteMagnitude = input.absoluteMagnitude,
            estimatedDiameter = input.estimatedDiameter,
            relativeVelocity = input.relativeVelocity,
            distanceFromEarth = input.distanceFromEarth,
            isPotentiallyHazardous = input.isPotentiallyHazardous
        )
    }
}

fun List<AsteroidEntity>.fromEntity(): List<Asteroid> {
    return map {
        AsteroidEntityMapper.fromEntity(it)
    }
}

fun List<Asteroid>.toEntity(): Array<AsteroidEntity> {
    return map {
        AsteroidEntityMapper.toEntity(it)
    }.toTypedArray()
}