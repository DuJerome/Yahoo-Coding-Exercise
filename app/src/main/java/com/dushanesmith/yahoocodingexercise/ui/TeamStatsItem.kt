package com.dushanesmith.yahoocodingexercise.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dushanesmith.yahoocodingexercise.data.model.TeamStats

@Composable
fun TeamStatsItem(
    teamStatsItem: TeamStats,
    isMainScreen: Boolean,
    onTeamSelected: (teamName: String) -> Unit
) {
    val lastColumnValue =
        if (isMainScreen) teamStatsItem.winPercentage.toString() + "%" else teamStatsItem.totalGamesPlayed.toString()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .padding(12.dp)
            .height(55.dp)
            .background(Color.White)
            .clickable(
                true,
                onClick = { onTeamSelected(teamStatsItem.teamName) }),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = teamStatsItem.teamName, modifier = Modifier.width(175.dp))
        Text(text = teamStatsItem.wins.toString(), modifier = Modifier.padding(end = 42.dp))
        Text(text = teamStatsItem.losses.toString(), modifier = Modifier.padding(end = 42.dp))
        Text(text = teamStatsItem.draws.toString(), modifier = Modifier.padding(end = 42.dp))
        Text(text = lastColumnValue)
    }
}