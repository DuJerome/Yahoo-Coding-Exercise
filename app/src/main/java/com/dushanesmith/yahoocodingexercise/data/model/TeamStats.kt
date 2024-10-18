package com.dushanesmith.yahoocodingexercise.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TeamStats(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val teamName: String,
    var wins: Int,
    var losses: Int,
    var draws: Int,
    var winPercentage: Long,
    var totalGamesPlayed: Int,
    var matchups: HashMap<String, TeamStats>
)