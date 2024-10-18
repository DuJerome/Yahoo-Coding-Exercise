package com.dushanesmith.yahoocodingexercise.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.dushanesmith.yahoocodingexercise.data.model.TeamStats
import com.dushanesmith.yahoocodingexercise.viewmodel.GamesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GamesDetailScreen(viewModel: GamesViewModel, navController: NavController, teamName: String?) {
    if (teamName == null) return
    val rememberedItems = remember { viewModel.games }
    val stateOfWinsSort = remember { mutableStateOf(1) }
    val stateOfLossesSort = remember { mutableStateOf(0) }
    val stateOfDrawsSort = remember { mutableStateOf(0) }
    val stateOfTotalGamesSort = remember { mutableStateOf(0) }
    val stateOfNameSort = remember { mutableStateOf(0) }
    val lastClicked = remember { mutableStateOf("wins")}
    val statItems = rememberedItems.collectAsLazyPagingItems().itemSnapshotList
    val teamStats = statItems.items.find { it.teamName == teamName }
    if (teamStats == null) return
    var matchupsStats: List<TeamStats> = teamStats.matchups.values.toList()
    val matchupsStatsOriginal = matchupsStats

    matchupsStats = if (stateOfNameSort.value == -1 && lastClicked.value == "name") {
        stateOfTotalGamesSort.value = 0
        stateOfWinsSort.value = 0
        stateOfLossesSort.value = 0
        stateOfDrawsSort.value = 0
        matchupsStats.sortedBy { it.teamName }
    } else if (stateOfNameSort.value == 1 && lastClicked.value == "name") {
        stateOfTotalGamesSort.value = 0
        stateOfWinsSort.value = 0
        stateOfLossesSort.value = 0
        stateOfDrawsSort.value = 0
        matchupsStats.sortedByDescending { it.teamName }
    } else if (stateOfNameSort.value == 0 && lastClicked.value == "name") {
        stateOfTotalGamesSort.value = 0
        stateOfWinsSort.value = 0
        stateOfLossesSort.value = 0
        stateOfDrawsSort.value = 0
        matchupsStatsOriginal
    } else if (stateOfLossesSort.value == 1 && lastClicked.value == "losses") {
        stateOfTotalGamesSort.value = 0
        stateOfWinsSort.value = 0
        stateOfNameSort.value = 0
        stateOfDrawsSort.value = 0
        matchupsStats.sortedByDescending { it.losses }
    } else if (stateOfLossesSort.value == -1 && lastClicked.value == "losses") {
        stateOfTotalGamesSort.value = 0
        stateOfWinsSort.value = 0
        stateOfNameSort.value = 0
        stateOfDrawsSort.value = 0
        matchupsStats.sortedBy { it.losses }
    } else if (stateOfLossesSort.value == 0 && lastClicked.value == "losses") {
        stateOfTotalGamesSort.value = 0
        stateOfWinsSort.value = 0
        stateOfNameSort.value = 0
        stateOfDrawsSort.value = 0
        matchupsStatsOriginal
    } else if (stateOfDrawsSort.value == 1 && lastClicked.value == "draws") {
        stateOfTotalGamesSort.value = 0
        stateOfWinsSort.value = 0
        stateOfNameSort.value = 0
        stateOfLossesSort.value = 0
        matchupsStats.sortedByDescending { it.draws }
    } else if (stateOfDrawsSort.value == -1 && lastClicked.value == "draws") {
        stateOfTotalGamesSort.value = 0
        stateOfWinsSort.value = 0
        stateOfNameSort.value = 0
        stateOfLossesSort.value = 0
        matchupsStats.sortedBy { it.draws }
    } else if (stateOfDrawsSort.value == 0 && lastClicked.value == "draws") {
        stateOfTotalGamesSort.value = 0
        stateOfWinsSort.value = 0
        stateOfNameSort.value = 0
        stateOfLossesSort.value = 0
        matchupsStatsOriginal
    } else if (stateOfTotalGamesSort.value == 1 && lastClicked.value == "total games") {
        stateOfDrawsSort.value = 0
        stateOfWinsSort.value = 0
        stateOfNameSort.value = 0
        stateOfLossesSort.value = 0
        matchupsStats.sortedByDescending { it.totalGamesPlayed }
    } else if (stateOfTotalGamesSort.value == -1 && lastClicked.value == "total games") {
        stateOfDrawsSort.value = 0
        stateOfWinsSort.value = 0
        stateOfNameSort.value = 0
        stateOfLossesSort.value = 0
        matchupsStats.sortedBy { it.totalGamesPlayed }
    } else if (stateOfTotalGamesSort.value == 0 && lastClicked.value == "total games") {
        stateOfDrawsSort.value = 0
        stateOfWinsSort.value = 0
        stateOfNameSort.value = 0
        stateOfLossesSort.value = 0
        matchupsStatsOriginal
    } else if (stateOfWinsSort.value == 0 && lastClicked.value == "wins") {
        stateOfDrawsSort.value = 0
        stateOfTotalGamesSort.value = 0
        stateOfNameSort.value = 0
        stateOfLossesSort.value = 0
        matchupsStatsOriginal
    } else if (stateOfWinsSort.value == -1 && lastClicked.value == "wins") {
        stateOfDrawsSort.value = 0
        stateOfTotalGamesSort.value = 0
        stateOfNameSort.value = 0
        stateOfLossesSort.value = 0
        matchupsStats.sortedBy { it.wins }
    } else {
        stateOfDrawsSort.value = 0
        stateOfTotalGamesSort.value = 0
        stateOfNameSort.value = 0
        stateOfLossesSort.value = 0
        matchupsStats.sortedByDescending { it.wins }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(teamStats.teamName)
                },
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    } else {
                        null
                    }
                }
            )
        },
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(6.dp, top = 120.dp)
                    .fillMaxWidth()
                ,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    lastClicked.value = "name"
                    if (stateOfNameSort.value == 1) {
                        stateOfNameSort.value = -1
                    } else if (stateOfNameSort.value == -1) {
                        stateOfNameSort.value = 0
                    } else {
                        stateOfNameSort.value = 1
                    }
                }) { Text("Name " + if (stateOfNameSort.value == 1) "↑" else if (stateOfNameSort.value == -1) "↓" else "-") }

                Button(onClick = {
                    lastClicked.value = "wins"
                    if (stateOfWinsSort.value == 1) {
                        stateOfWinsSort.value = -1
                    } else if (stateOfWinsSort.value == -1) {
                        stateOfWinsSort.value = 0
                    } else {
                        stateOfWinsSort.value = 1
                    }
                }) { Text("W " + if (stateOfWinsSort.value == 1) "↑" else if (stateOfWinsSort.value == -1) "↓" else "-") }

                Button(onClick = {
                    lastClicked.value = "losses"
                    if (stateOfLossesSort.value == 1) {
                        stateOfLossesSort.value = -1
                    } else if (stateOfLossesSort.value == -1) {
                        stateOfLossesSort.value = 0
                    } else {
                        stateOfLossesSort.value = 1
                    }
                }) { Text("L " + if (stateOfLossesSort.value == 1) "↑" else if (stateOfLossesSort.value == -1) "↓" else "-") }

                Button(onClick = {
                    lastClicked.value = "draws"
                    if (stateOfDrawsSort.value == 1) {
                        stateOfDrawsSort.value = -1
                    } else if (stateOfDrawsSort.value == -1) {
                        stateOfDrawsSort.value = 0
                    } else {
                        stateOfDrawsSort.value = 1
                    }
                }) { Text("D " + if (stateOfDrawsSort.value == 1) "↑" else if (stateOfDrawsSort.value == -1) "↓" else "-") }

                Button(onClick = {
                    lastClicked.value = "total games"
                    if (stateOfTotalGamesSort.value == 1) {
                        stateOfTotalGamesSort.value = -1
                    } else if (stateOfTotalGamesSort.value == -1) {
                        stateOfTotalGamesSort.value = 0
                    } else {
                        stateOfTotalGamesSort.value = 1
                    }
                }) { Text("T " + if (stateOfTotalGamesSort.value == 1) "↑" else if (stateOfTotalGamesSort.value == -1) "↓" else "-") }
            }
            LazyColumn(
                modifier = Modifier
                    .navigationBarsPadding()
                    .fillMaxSize()
            ) {
                item {
                    TeamStatsHeaderMainScreen(false)
                }
                if (!matchupsStats.isNullOrEmpty()) {
                    items(matchupsStats.size) { it ->
                        TeamStatsItem(matchupsStats[it], false, { Unit })
                    }
                }
            }
        }
    }
}