package com.componentes.game_2048.view.utils

import javax.inject.Inject

const val EMPTY_VALUE = -1
class CreateGameBoard @Inject constructor(
    private val addTileCase: AddTilesInBoard
) {
    fun gameBoard(size: Int): MutableList<MutableList<Int>> {
        var boardGame = MutableList(size) {
            MutableList(size) {
                EMPTY_VALUE
            }
        }

        boardGame = addTileCase.addTile(boardGame = boardGame)
        boardGame = addTileCase.addTile(boardGame = boardGame)


        return boardGame
    }
}