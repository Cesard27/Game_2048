package com.componentes.game_2048.view.utils

import com.componentes.game_2048.model.Direction
import com.componentes.game_2048.model.Direction.*
import com.componentes.game_2048.view.utils.MoveNumberResult.*
import javax.inject.Inject

class TileMovement @Inject constructor(
    private val addingTileCase: AddTilesInBoard,
    private val possibleMoveCase: isMovingPossible
){

    fun moveNumbers(
        boardGame: MutableList<MutableList<Int>>,
        direction: Direction,
        isGameOver: Boolean): MoveNumberResult {

        var boardGameAfterMove = getBoardGameAfterMove(boardGame, direction) ?:
            return MoveNumberResult(boardGame, isGameOver)

        boardGameAfterMove = addingTileCase.addTile(boardGameAfterMove)


        return possibleMoveCase.existMove(boardGameAfterMove)

    }

    private fun getBoardGameAfterMove(
        boardGamePrime: MutableList<MutableList<Int>>,
        direction: Direction
    ): MutableList<MutableList<Int>>?{
        val boardSize = boardGamePrime.size
        val boardGame = boardGamePrime.copy()

        val boardGameAfterMove = when(direction) {
            LEFT, RIGHT -> {
                getHorizontalMovement(boardSize, boardGame, direction)
            }

            UP, DOWN -> {
                getVerticalMovement(boardSize, boardGame, direction)
            }

            NONE -> boardGame
        }

        if (direction == NONE) return null
        return if (boardGamePrime.isTheSameBoard(boardGameAfterMove)) null else boardGameAfterMove
    }
}

private fun getHorizontalMovement(
    boardSize: Int,
    boardGame: MutableList<MutableList<Int>>,
    direction: Direction
): MutableList<MutableList<Int>>{
    for (rowIndex in 0..<boardSize) {
        // takin row lines
        val row = boardGame[rowIndex]

        //delete default_value
        for (index in row.size-1 downTo 0) {
            if (row[index]== DEFAULT_VALUE) {
                row.removeAt(index)
            }
        }

        val newRow = mutableListOf<Int>()

        if (direction == RIGHT) {
            row.reverse()
        }

        // combine 'n' delete
        while (row.isNotEmpty()) {
            if (row.size > 1 && row[0] == row[1]) {
                newRow.add(row[0] * 2)
                row.removeAt(1)
                row.removeAt(0)
                continue
            }
            newRow.add(row[0])
            row.removeAt(0)
        }

        //
        for (repetition in 0..<boardSize - newRow.size) {
            newRow.add(DEFAULT_VALUE)
        }

        //
        if (direction == RIGHT) {
            newRow.reverse()
        }
        boardGame[rowIndex] = newRow
    }
    return boardGame
}

private fun getVerticalMovement(
    boardSize: Int,
    boardGame: MutableList<MutableList<Int>>,
    direction: Direction
): MutableList<MutableList<Int>> {

    for (columnIndex in 0..<boardSize) {
        val column = mutableListOf<Int>()

        for (rowIndex in 0..<boardSize) {
            val cell = boardGame[rowIndex][columnIndex]
            if (cell != DEFAULT_VALUE) {
                column.add(cell)
            }
        }

        val newColumn = mutableListOf<Int>()
        if (direction == DOWN) {
            column.reverse()
        }

        while (column.isNotEmpty()) {
            if (column.size > 1 && column[0] == column[1]) {
                newColumn.add(column[0] * 2)
                column.removeAt(1)
                column.removeAt(0)
                continue
            }
            newColumn.add(column[0])
            column.removeAt(0)
        }

        for (repetition in 0..<boardSize - newColumn.size) {
            newColumn.add(DEFAULT_VALUE)
        }

        if (direction == DOWN) {
            newColumn.reverse()
        }

        for (rowIndex in 0..<boardSize) {
            boardGame[rowIndex][columnIndex] = newColumn[rowIndex]
        }
    }
    return boardGame
}

fun MutableList<MutableList<Int>>.copy(): MutableList<MutableList<Int>>{
    val list = mutableListOf<MutableList<Int>>()

    for (rowIndex in 0..<size) {
        val subList = mutableListOf<Int>()
        for (cellIndex in 0..<size) {
            subList.add(this[rowIndex][cellIndex])
        }
        list.add(subList)
    }
    return list
}

fun MutableList<MutableList<Int>>.isTheSameBoard(other: MutableList<MutableList<Int>>): Boolean {
    for (rowIndex in 0..<size) {
        for (cellIndex in 0..<size) {
            if (this[rowIndex][cellIndex] != other[rowIndex][cellIndex]){
                return false
            }
        }
    }
    return true
}