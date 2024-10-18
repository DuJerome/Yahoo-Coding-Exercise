package com.dushanesmith.yahoocodingexercise.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.dushanesmith.yahoocodingexercise.data.model.TeamStats

@Dao
interface TeamStatsDao {

    @Insert
    suspend fun insertAll(games: List<TeamStats>)

    @Query("SELECT * FROM teamStats")
    fun pagingSource(): PagingSource<Int, TeamStats>
}