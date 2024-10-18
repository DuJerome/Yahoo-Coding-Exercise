package com.dushanesmith.yahoocodingexercise.viewmodel

import androidx.lifecycle.ViewModel
import com.dushanesmith.yahoocodingexercise.data.GamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val gamesRepository: GamesRepository
) : ViewModel() {
    val games = gamesRepository.getGames()
}