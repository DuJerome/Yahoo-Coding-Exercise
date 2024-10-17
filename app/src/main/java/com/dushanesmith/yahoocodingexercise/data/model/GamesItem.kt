package com.dushanesmith.yahoocodingexercise.data.model

data class GamesItem(
    val awayScore: Int,
    val awayTeamId: String,
    val awayTeamName: String,
    val gameId: String,
    val homeScore: Int,
    val homeTeamId: String,
    val homeTeamName: String
)