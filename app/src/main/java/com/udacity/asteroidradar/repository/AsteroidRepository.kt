package com.udacity.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.fromEntity
import com.udacity.asteroidradar.database.toEntity
import com.udacity.asteroidradar.shared.api.AsteroidRetrofitService
import com.udacity.asteroidradar.shared.date.AppDate
import com.udacity.asteroidradar.shared.model.Asteroid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class AsteroidRepository(private val database: AsteroidDatabase) {
    val allAsteroids: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidDao.getAsteroids()) {
            it.fromEntity()
        }

    val todayAsteroids: LiveData<List<Asteroid>> =
        Transformations.map(
            database.asteroidDao.getAsteroidsDay(
                AppDate.format(
                    LocalDate.now(),
                    DateTimeFormatter.ISO_DATE
                )
            )
        ) {
            it.fromEntity()
        }

    val weekAsteroids: LiveData<List<Asteroid>> = Transformations.map(
        database.asteroidDao.getAsteroidsDate(
            AppDate.format(
                LocalDate.now(),
                DateTimeFormatter.ISO_DATE
            ),
            AppDate.format(
                LocalDate.now().minusDays(AppDate.DAYS),
                DateTimeFormatter.ISO_DATE
            )
        )
    ) {
        it.fromEntity()
    }

    suspend fun loadAsteroids() {
        withContext(Dispatchers.IO) {
            try {
                val response =
                    AsteroidRetrofitService.retrofitService.asteroids()
                val nextSevenDaysFormattedDates = AppDate.nextWeekDates()

                val items =
                    response.data
                        .filter { nextSevenDaysFormattedDates.contains(it.key) }
                        .flatMap {
                            it.value.map { item ->
                                Pair(it.key, item)
                            }
                        }
                        .map {
                            val date = it.first
                            val item = it.second
                            Asteroid(
                                id = item.id,
                                codename = item.name,
                                closeApproachDate = date,
                                absoluteMagnitude = item.absoluteMagnitudeH.toString(),
                                estimatedDiameter = item.estimatedDiameter.kilometers.estimatedDiameterMax.toString(),
                                relativeVelocity = item.closeApproachData[0].relativeVelocity.kilometersPerSecond,
                                distanceFromEarth = item.closeApproachData[0].missDistance.astronomical,
                                isPotentiallyHazardous = true,
                            )
                        }
                database.asteroidDao.insertAll(items.toEntity())
                Log.d("Refresh Asteroids", "Success")
            } catch (err: Exception) {
                Log.e("Failed: AsteroidRepFile", err.message.toString())
            }
        }
    }
}