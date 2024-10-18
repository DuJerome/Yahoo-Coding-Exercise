package com.dushanesmith.yahoocodingexercise.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.dushanesmith.yahoocodingexercise.Constants.baseURL
import com.dushanesmith.yahoocodingexercise.data.GamesRepository
import com.dushanesmith.yahoocodingexercise.data.GamesRepositoryImpl
import com.dushanesmith.yahoocodingexercise.data.api.GamesApi
import com.dushanesmith.yahoocodingexercise.data.local.TeamStatsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideGamesDatabase(@ApplicationContext context: Context): TeamStatsDatabase{
        return Room.databaseBuilder(
            context,
            TeamStatsDatabase::class.java,
            "games.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideGamesRepository(
        teamStatsDatabase: TeamStatsDatabase,
        gamesApi: GamesApi
    ): GamesRepository {
        return GamesRepositoryImpl(teamStatsDatabase, gamesApi)
    }
}