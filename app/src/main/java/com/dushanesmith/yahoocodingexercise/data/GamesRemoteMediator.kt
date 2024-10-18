package com.dushanesmith.yahoocodingexercise.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import coil.network.HttpException
import com.dushanesmith.yahoocodingexercise.data.api.GamesApi
import com.dushanesmith.yahoocodingexercise.data.local.TeamStatsDatabase
import com.dushanesmith.yahoocodingexercise.data.model.GamesItem
import com.dushanesmith.yahoocodingexercise.data.model.TeamStats
import okio.IOException

@OptIn(ExperimentalPagingApi::class)
class GamesRemoteMediator(
    private val gamesDB: TeamStatsDatabase,
    private val gamesApi: GamesApi
) : RemoteMediator<Int, TeamStats>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TeamStats>
    ): MediatorResult {
        return try {
//            val loadKey = when(loadType){
//                LoadType.REFRESH -> 1
//                LoadType.PREPEND -> null
//                LoadType.APPEND -> {
//                    val lastItem = state.lastItemOrNull()
//                    if(lastItem == null){
//                        1
//                    }else{
//                        (lastItem.id / state.config.pageSize) +1
//                    }
//
//                }
//            }
            val games = gamesApi.getGames() as List<GamesItem>
            // I chose to format the data here because it is withing a thread while if I did this work
            // in another part of the app such as the view model I would not have access to coroutines
            val mappingOfGames = HashMap<String, TeamStats>()
            for (i in 0 until games.size) {
                val game = games[i]
                if (mappingOfGames.containsKey(game.homeTeamName)) {
                    val entry = mappingOfGames.get(game.homeTeamName)!!
                    val matchupStandings =
                        if (entry.matchups.containsKey(game.awayTeamName)) {
                            entry.matchups.getValue(
                                game.awayTeamName
                            )
                        } else {
                            TeamStats(
                                teamName = game.awayTeamName,
                                wins = 0,
                                losses = 0,
                                draws = 0,
                                totalGamesPlayed = 0,
                                winPercentage = 0,
                                matchups = hashMapOf()
                            )
                        }
                    entry.totalGamesPlayed++
                    matchupStandings.totalGamesPlayed++
                    if (game.homeScore > game.awayScore) {
                        entry.wins++
                        matchupStandings.losses++
                    } else if (game.homeScore == game.awayScore) {
                        entry.draws++
                        matchupStandings.draws++
                    } else {
                        entry.losses++
                        matchupStandings.wins++
                    }
                    entry.winPercentage =
                        Math.round((entry.wins.toDouble() / entry.totalGamesPlayed) * 100)
                    matchupStandings.winPercentage =
                        Math.round((matchupStandings.wins.toDouble() / matchupStandings.totalGamesPlayed) * 100)
                    entry.matchups.put(game.awayTeamName, matchupStandings)
                    mappingOfGames.put(game.homeTeamName, entry)
                } else {
                    val entry = TeamStats(
                        teamName = game.homeTeamName,
                        wins = 0,
                        losses = 0,
                        draws = 0,
                        winPercentage = 0,
                        totalGamesPlayed = 0,
                        matchups = hashMapOf()
                    )
                    val matchupStandings = TeamStats(
                        teamName = game.awayTeamName,
                        wins = 0,
                        losses = 0,
                        draws = 0,
                        winPercentage = 0,
                        totalGamesPlayed = 0,
                        matchups = hashMapOf()
                    )
                    entry.totalGamesPlayed++
                    matchupStandings.totalGamesPlayed++
                    if (game.homeScore > game.awayScore) {
                        entry.wins++
                        matchupStandings.losses++
                    } else if (game.homeScore == game.awayScore) {
                        entry.draws++
                        matchupStandings.draws++
                    } else {
                        entry.losses++
                        matchupStandings.wins++
                    }
                    entry.winPercentage =
                        Math.round((entry.wins.toDouble() / entry.totalGamesPlayed) * 100)
                    matchupStandings.winPercentage =
                        Math.round((matchupStandings.wins.toDouble() / matchupStandings.totalGamesPlayed) * 100)
                    entry.matchups.put(game.awayTeamName, matchupStandings)
                    mappingOfGames.put(game.homeTeamName, entry)
                }

                if (mappingOfGames.containsKey(game.awayTeamName)) {
                    val entry = mappingOfGames.get(game.awayTeamName)!!
                    val matchupStandings =
                        if (entry.matchups.containsKey(game.homeTeamName)) {
                            entry.matchups.getValue(
                                game.homeTeamName
                            )
                        } else {
                            TeamStats(
                                teamName = game.homeTeamName,
                                wins = 0,
                                losses = 0,
                                draws = 0,
                                totalGamesPlayed = 0,
                                winPercentage = 0,
                                matchups = hashMapOf()
                            )
                        }
                    entry.totalGamesPlayed++
                    matchupStandings.totalGamesPlayed++
                    if (game.awayScore > game.homeScore) {
                        entry.wins++
                        matchupStandings.losses++
                    } else if (game.awayScore == game.homeScore) {
                        entry.draws++
                        matchupStandings.draws++
                    } else {
                        entry.losses++
                        matchupStandings.wins++
                    }
                    entry.winPercentage =
                        Math.round((entry.wins.toDouble() / entry.totalGamesPlayed) * 100)
                    matchupStandings.winPercentage =
                        Math.round((matchupStandings.wins.toDouble() / matchupStandings.totalGamesPlayed) * 100)
                    entry.matchups.put(game.homeTeamName, matchupStandings)
                    mappingOfGames.put(game.awayTeamName, entry)
                } else {
                    val entry = TeamStats(
                        teamName = game.awayTeamName,
                        wins = 0,
                        losses = 0,
                        draws = 0,
                        winPercentage = 0,
                        totalGamesPlayed = 0,
                        matchups = hashMapOf()
                    )
                    val matchupStandings = TeamStats(
                        teamName = game.homeTeamName,
                        wins = 0,
                        losses = 0,
                        draws = 0,
                        totalGamesPlayed = 0,
                        winPercentage = 0,
                        matchups = hashMapOf()
                    )
                    entry.totalGamesPlayed++
                    matchupStandings.totalGamesPlayed++
                    if (game.awayScore > game.homeScore) {
                        entry.wins++
                        matchupStandings.losses++
                    } else if (game.awayScore == game.homeScore) {
                        entry.draws++
                        matchupStandings.draws++
                    } else {
                        entry.losses++
                        matchupStandings.wins++
                    }
                    entry.winPercentage =
                        Math.round((entry.wins.toDouble() / entry.totalGamesPlayed) * 100)
                    matchupStandings.winPercentage =
                        Math.round((matchupStandings.wins.toDouble() / matchupStandings.totalGamesPlayed) * 100)
                    entry.matchups.put(game.homeTeamName, matchupStandings)
                    mappingOfGames.put(game.awayTeamName, entry)
                }
            }
            /*
            Hell0, at this point in the code I get 70+ items from the backend but only
            29 of those get pulled from the remote mediator to the frontend. I dont know
            the cause but it may and I think it is likely that there is a bug in the remote
            mediator. I am not trying to avoid blame but it is odd because the items that are
            missing return as null.
             */
            gamesDB.dao.insertAll(mappingOfGames.values.toList())
            MediatorResult.Success(true) // All the games are retrieved and processed at once so this is true
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}