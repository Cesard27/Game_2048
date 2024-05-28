package com.componentes.game_2048.view.utils

import kotlin.random.Random

val INIT_NUMBERS = listOf(2, 2, 2, 2, 4)

class AddTilesInBoard{
    fun addTile(boardGame: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
        val position = getValidPosition(boardGame) ?: return boardGame
        val number = getTile()

        boardGame[position.first][position.second] = number

        return boardGame
    }

    private fun getTile(): Int {
        val randomIndex = Random.nextInt(INIT_NUMBERS.size)

        return INIT_NUMBERS[randomIndex]
    }

    private fun getValidPosition(boardGame: MutableList<MutableList<Int>>): Pair<Int, Int>? {
        val lineSize = boardGame.size
        val validCells = mutableListOf<Pair<Int, Int>>()

        for (rowIndex in 0..<lineSize) {
            for (cellIndex in 0..<lineSize) {
                val cell = boardGame[rowIndex][cellIndex]
                if (cell == EMPTY_VALUE) {
                    validCells.add(Pair(rowIndex, cellIndex))
                }
            }
        }

        if (validCells.isEmpty()) {
            return null
        }

        val position = Random.nextInt(validCells.size)
        return validCells[position]
    }

    private fun getNumber(): Int{
        val randomIndex = Random.nextInt(INIT_NUMBERS.size)
        return INIT_NUMBERS[randomIndex]
    }
}

