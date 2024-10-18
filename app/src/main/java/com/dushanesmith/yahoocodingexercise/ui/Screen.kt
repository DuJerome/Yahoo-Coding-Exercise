package com.dushanesmith.yahoocodingexercise.ui.theme

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object DetailsScreen: Screen("details_screen")

    fun withArguments(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/${arg}")
            }
        }
    }
}