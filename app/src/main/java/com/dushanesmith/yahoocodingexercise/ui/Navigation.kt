package com.dushanesmith.yahoocodingexercise.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dushanesmith.yahoocodingexercise.viewmodel.GamesViewModel

@Composable
fun Navigation(navController: NavHostController, viewModel: GamesViewModel){
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route) {
            OverallStanding(viewModel, navController)
        }

        composable(route = Screen.DetailsScreen.route + "/{teamName}",
            arguments = listOf(navArgument("teamName"){
                NavType.StringType
                nullable = false
            })
        ) { entry ->
            GamesDetailScreen(viewModel, navController, teamName = entry.arguments?.getString("teamName"))
        }
    }
}