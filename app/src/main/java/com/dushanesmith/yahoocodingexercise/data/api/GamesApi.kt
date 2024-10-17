package com.dushanesmith.yahoocodingexercise.data.api

import com.dushanesmith.yahoocodingexercise.data.model.Games
import retrofit2.http.GET

interface GamesApi {

    @GET("soccer_game_results.json")
    suspend fun getGames(): Games
}