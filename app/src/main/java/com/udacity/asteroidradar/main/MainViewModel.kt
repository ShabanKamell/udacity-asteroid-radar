package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.repository.AsteroidRepository
import com.udacity.asteroidradar.shared.api.AsteroidRetrofitService
import com.udacity.asteroidradar.shared.model.Asteroid
import com.udacity.asteroidradar.shared.model.AsteroidFilter
import com.udacity.asteroidradar.shared.model.PictureOfDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val asteroidRepository = AsteroidRepository(AsteroidDatabase.create(application))

    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfDay

    private val _navigateToDetailAsteroid = MutableLiveData<Asteroid?>()
    val navigateToDetailAsteroid: LiveData<Asteroid?>
        get() = _navigateToDetailAsteroid

    private var _filterAsteroid = MutableLiveData(AsteroidFilter.ALL)

    val asteroids = Transformations.switchMap(_filterAsteroid) {
        when (it!!) {
            AsteroidFilter.WEEK -> asteroidRepository.weekAsteroids
            AsteroidFilter.TODAY -> asteroidRepository.todayAsteroids
            AsteroidFilter.ALL -> asteroidRepository.allAsteroids
        }
    }

    init {
        viewModelScope.launch {
            asteroidRepository.loadAsteroids()
            loadPictureOfDay()
        }
    }

    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToDetailAsteroid.value = asteroid
    }

    fun onAsteroidNavigated() {
        _navigateToDetailAsteroid.value = null
    }

    fun filter(filter: AsteroidFilter) {
        _filterAsteroid.postValue(filter)
    }

    private suspend fun loadPictureOfDay() {
        withContext(Dispatchers.IO) {
            try {
                _pictureOfDay.postValue(
                    AsteroidRetrofitService.retrofitService.pictureOfTheDay()
                )
            } catch (err: Exception) {
                Log.e("loadPictureOfDay", err.printStackTrace().toString())
            }
        }
    }
}