package com.dushanesmith.yahoocodingexercise.data

import androidx.paging.PagingData
import com.dushanesmith.yahoocodingexercise.data.model.TeamStats
import kotlinx.coroutines.flow.Flow

interface GamesRepository {

    fun getGames(): Flow<PagingData<TeamStats>>
}