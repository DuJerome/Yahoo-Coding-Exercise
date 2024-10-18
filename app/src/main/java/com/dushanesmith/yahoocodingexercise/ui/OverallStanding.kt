package com.dushanesmith.yahoocodingexercise.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.dushanesmith.yahoocodingexercise.viewmodel.GamesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverallStanding(viewModel: GamesViewModel, navController: NavHostController) {
    val rememberedItems = remember { viewModel.games }
    val statItems = rememberedItems.collectAsLazyPagingItems().itemSnapshotList
    val listOfStatItems = statItems.sortedByDescending { it?.winPercentage }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Overall Standings")
                }
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(top = 65.dp)
                .fillMaxSize()
        ) {
            item {
                TeamStatsHeaderMainScreen(true)
            }
            if (!listOfStatItems.isNullOrEmpty()) {
                items(listOfStatItems.size) { it ->
                    listOfStatItems.get(it)?.let { it1 ->
                        TeamStatsItem(
                            it1,
                            true,
                            { navController.navigate(Screen.DetailsScreen.withArguments(it1.teamName)) })
                    }
                }
            }
        }
    }
}