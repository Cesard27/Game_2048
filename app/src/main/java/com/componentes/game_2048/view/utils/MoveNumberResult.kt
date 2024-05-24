package com.componentes.game_2048.view.utils


data class MoveNumberResult(
    var boardGame: MutableList<MutableList<Int>>,
    var isGameOver: Boolean = false
)