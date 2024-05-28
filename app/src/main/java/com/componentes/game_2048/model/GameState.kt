package com.componentes.game_2048.model


import com.componentes.game_2048.model.GameStatus.*
import com.componentes.game_2048.viewmodel.WINNING_NUMBER

data class GameState(
    val board: MutableList<MutableList<Int>> = mutableListOf(),
    var gameStatus: GameStatus = IS_PLAYING,
    var winningNumber: Int = WINNING_NUMBER,
    var score: Int = 0
){
    fun toMap(): Map<String, Any> {
        val boardList = board.flatten() // Convertir el tablero en una lista plana
        return mapOf(
            "board" to boardList,
            "gameStatus" to gameStatus.ordinal, // Guardar el índice del enum en lugar del valor
            "winningNumber" to winningNumber,
            "score" to score
        )
    }

    companion object {
        fun fromMap(map: Map<String, Any>): GameState {
            val boardList = map["board"] as List<Long>
            val boardSize = Math.sqrt(boardList.size.toDouble()).toInt()
            val board = MutableList(boardSize) { row ->
                MutableList(boardSize) { column ->
                    boardList[row * boardSize + column].toInt()
                }
            }
            val gameStatusOrdinal = map["gameStatus"] as Long
            val gameStatus = GameStatus.values()[gameStatusOrdinal.toInt()]
            val winningNumber = (map["winningNumber"] as Long).toInt()
            val score = (map["score"] as Long).toInt()
            return GameState(board, gameStatus, winningNumber, score)
        }
    }

}

enum class GameStatus {
    IS_PLAYING,
    GAME_OVER,
    PLAYER_WINS
}