package com.dushanesmith.yahoocodingexercise.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dushanesmith.yahoocodingexercise.data.model.TeamStats

@Database(
    entities = [TeamStats::class],
    version = 6,
)
@TypeConverters(Converters::class)
abstract class TeamStatsDatabase: RoomDatabase() {
    abstract val dao: TeamStatsDao
}