package com.componentes.game_2048.view.utils

import com.componentes.game_2048.model.GameStatus.*

class isMovingPossible {
    fun existNCheckToContinue(
        boardGame: MutableList<MutableList<Int>>,
        winningNumber: Int
    ): MoveNumberResult {

        val boarSize = boardGame.size
        val currentPlayingBoardGame = MoveNumberResult(boardGame, winningNumber = winningNumber)

        // validations for possibles next moves

        for (rowIndex in 0..<boarSize) {
            // validations in rows
            val row = boardGame[rowIndex]

            if (row[0] == EMPTY_VALUE)
                return currentPlayingBoardGame

            for (index in 0..<row.size - 1) {
                val currentTile = row[index]
                val nextTile = row[index + 1]

                if (nextTile == EMPTY_VALUE || currentTile == nextTile)
                    return currentPlayingBoardGame
            }
        }


        for (columnIndex in 0..<boarSize -1) {
            // validations in columns
            val firstTile = boardGame[0][columnIndex]

            if (firstTile == EMPTY_VALUE)
                return currentPlayingBoardGame

            for (rowIndex in 0..<boarSize - 1) {
                val currentTile = boardGame[rowIndex][columnIndex]
                val nextTile = boardGame[rowIndex + 1][columnIndex]

                if (nextTile == EMPTY_VALUE || currentTile == nextTile)
                    return currentPlayingBoardGame
            }
        }

        return MoveNumberResult(
            boardGame = boardGame,
            gameStatus = GAME_OVER,
            winningNumber = winningNumber
        )
    }
}