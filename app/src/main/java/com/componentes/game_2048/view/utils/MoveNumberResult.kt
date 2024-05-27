package com.componentes.game_2048.view.utils

import com.componentes.game_2048.model.GameStatus
import com.componentes.game_2048.model.GameStatus.*


data class MoveNumberResult(
    var boardGame: MutableList<MutableList<Int>>,
    var gameStatus: GameStatus = IS_PLAYING,
    var winningNumber: Int
){
    private fun obtenerFichasCombinadas(oldBoard: MutableList<MutableList<Int>>, newBoard: MutableList<MutableList<Int>>): List<Int> {
        val fichasCombinadas = mutableListOf<Int>()
        for (i in 0 until oldBoard.size) {
            for (j in 0 until oldBoard[i].size) {
                if (oldBoard[i][j] != newBoard[i][j]) {
                    val fichaNueva = newBoard[i][j]
                    if (fichaNueva != 0 && fichaNueva % 2 == 0) {
                        // Se ha combinado una ficha, agregar su valor al listado
                        fichasCombinadas.add(fichaNueva)
                    }
                }
            }
        }
        return fichasCombinadas
    }
}