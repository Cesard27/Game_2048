package com.componentes.game_2048.model

import com.componentes.game_2048.view.components.DEFAULT_VALUE

enum class Direction { UP,DOWN, LEFT, RIGHT, NONE }

class TileMovement{
    fun moveNumbers(boardGame: MutableList<MutableList<Int>>, direction: Direction): MutableList<MutableList<Int>>?{
        val boardSize = boardGame.size
        return when(direction) {
            Direction.LEFT, Direction.RIGHT -> {
                getHorizontalMovement(boardSize, boardGame, direction)
            }


            Direction.UP, Direction.DOWN -> {
                getVerticalMovement(boardSize, boardGame, direction)
            }

            else -> null
        }
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

        if (direction == Direction.RIGHT) {
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
        if (direction == Direction.RIGHT) {
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
        if (direction == Direction.DOWN) {
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

        if (direction == Direction.DOWN) {
            newColumn.reverse()
        }

        for (rowIndex in 0..<boardSize) {
            boardGame[rowIndex][columnIndex] = newColumn[rowIndex]
        }
    }
    return boardGame
}