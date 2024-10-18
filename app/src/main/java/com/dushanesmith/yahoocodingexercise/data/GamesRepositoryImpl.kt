package com.dushanesmith.yahoocodingexercise.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dushanesmith.yahoocodingexercise.data.api.GamesApi
import com.dushanesmith.yahoocodingexercise.data.local.TeamStatsDatabase
import com.dushanesmith.yahoocodingexercise.data.model.GamesItem
import com.dushanesmith.yahoocodingexercise.data.model.TeamStats
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val teamStatsDatabase: TeamStatsDatabase,
    private val gamesApi: GamesApi
) : GamesRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getGames(): Flow<PagingData<TeamStats>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = GamesRemoteMediator(
                gamesDB = teamStatsDatabase,
                gamesApi = gamesApi
            ),
            pagingSourceFactory = { teamStatsDatabase.dao.pagingSource() }
        ).flow
    }
}