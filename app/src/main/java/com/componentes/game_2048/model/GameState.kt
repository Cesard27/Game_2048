package com.componentes.game_2048.model

data class GameState(
    val board: MutableList<MutableList<Int>> = emptyList<MutableList<Int>>().toMutableList()
)