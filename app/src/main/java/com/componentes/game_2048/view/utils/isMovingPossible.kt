package com.componentes.game_2048.view.utils

class isMovingPossible {
    fun existMove(boardGame: MutableList<MutableList<Int>>): MoveNumberResult {
        val boarSize = boardGame.size

        for (rowIndex in 0..<boarSize) {
            val row = boardGame[rowIndex]

            if (row[0] == DEFAULT_VALUE) return MoveNumberResult(boardGame)
        }
        return MoveNumberResult(
            boardGame = boardGame,
            isGameOver = true
        )
    }
}