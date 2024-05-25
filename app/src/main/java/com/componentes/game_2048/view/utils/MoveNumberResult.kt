package com.componentes.game_2048.view.utils

import com.componentes.game_2048.model.GameStatus
import com.componentes.game_2048.model.GameStatus.*


data class MoveNumberResult(
    var boardGame: MutableList<MutableList<Int>>,
    var gameStatus: GameStatus = IS_PLAYING,
    var winningNumber: Int
)