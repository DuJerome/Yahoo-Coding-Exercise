package com.dushanesmith.yahoocodingexercise.dependencyinjection

import com.dushanesmith.yahoocodingexercise.Constants.baseURL
import com.dushanesmith.yahoocodingexercise.data.api.GamesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGamesApi(): GamesApi{
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GamesApi::class.java)
    }
}