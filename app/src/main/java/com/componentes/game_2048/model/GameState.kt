package com.componentes.game_2048.model


import com.componentes.game_2048.model.GameStatus.*
import com.componentes.game_2048.viewmodel.WINNING_NUMBER

data class GameState(
    val board: MutableList<MutableList<Int>> = mutableListOf(),
    var gameStatus: GameStatus = IS_PLAYING,
    var winningNumber: Int = WINNING_NUMBER,
    var score: Int = 0
)

enum class GameStatus {
    IS_PLAYING,
    GAME_OVER,
    PLAYER_WINS
}