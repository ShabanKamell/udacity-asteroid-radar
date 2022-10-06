package com.udacity.asteroidradar.shared.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.shared.model.PictureOfDay
import com.udacity.asteroidradar.shared.model.AsteroidsResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface AsteroidService {
    @GET("neo/rest/v1/feed")
    suspend fun asteroids(@Query("api_key") api_key: String = AsteroidRetrofitService.API_KEY): AsteroidsResponse

    @GET("planetary/apod")
    suspend fun pictureOfTheDay(@Query("api_key") api_key: String = AsteroidRetrofitService.API_KEY): PictureOfDay
}

object AsteroidRetrofitService {
    const val API_KEY = "3fGSgAfdtk9vx4xmnRDAANOOfpdKSR2PJzwsYsEt"
    val retrofitService: AsteroidService by lazy { createService() }

    private fun createService(): AsteroidService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create(AsteroidService::class.java)
    }
}