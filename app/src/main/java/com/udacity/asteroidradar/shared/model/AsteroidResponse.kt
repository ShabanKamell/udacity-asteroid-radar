package com.udacity.asteroidradar.shared.model

import com.squareup.moshi.Json

data class AsteroidsResponse(
    @Json(name = "near_earth_objects")
    val data: Map<String, List<AsteroidData>>
)

data class AsteroidData(
    @Json(name = "id")
    var id: String,

    @Json(name = "name")
    var name: String,

    @Json(name = "absolute_magnitude_h")
    var absoluteMagnitudeH: Double,

    @Json(name = "estimated_diameter")
    var estimatedDiameter: EstimatedDiameter,

    @Json(name = "close_approach_data")
    var closeApproachData: List<CloseApproachDatum>,

    @Json(name = "is_potentially_hazardous_asteroid")
    var isPotentiallyHazardous: Boolean
)

data class CloseApproachDatum(
    @Json(name = "close_approach_date")
    var closeApproachDate: String,

    @Json(name = "relative_velocity")
    var relativeVelocity: RelativeVelocity,

    @Json(name = "miss_distance")
    var missDistance: MissDistance,
)

data class EstimatedDiameter(
    @Json(name = "kilometers")
    var kilometers: Kilometers,
)

data class Kilometers(
    @Json(name = "estimated_diameter_max")
    var estimatedDiameterMax: Double,
)

data class MissDistance(
    @Json(name = "astronomical")
    var astronomical: String,
)

data class RelativeVelocity(
    @Json(name = "kilometers_per_second")
    var kilometersPerSecond: String,
)


