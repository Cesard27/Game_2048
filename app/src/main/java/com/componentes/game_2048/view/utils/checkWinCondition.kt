package com.componentes.game_2048.view.utils

import com.componentes.game_2048.model.GameStatus.*
import com.componentes.game_2048.viewmodel.WINNING_NUMBER

class CheckWinCondition {

    fun checkingCondition(
        result: MoveNumberResult,
        winningNumber: Int
    ): MoveNumberResult{
        if (result.gameStatus != IS_PLAYING) return result

        if (!conditionValidator(result.boardGame, winningNumber)) return result

        result.apply {
            gameStatus = PLAYER_WINS
        }

        return result
    }
}

private fun conditionValidator(boardGame: MutableList<MutableList<Int>>, winningNumber: Int): Boolean {
    for (rowIndex in 0..<boardGame.size) {
        for (index in 0..<boardGame.size) {
            val number = boardGame[rowIndex][index]
            if (number == winningNumber) {
                return true
            }
        }
    }
    return false
}