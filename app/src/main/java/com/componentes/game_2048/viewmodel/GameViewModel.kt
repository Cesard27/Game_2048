package com.componentes.game_2048.viewmodel

import androidx.lifecycle.ViewModel
import com.componentes.game_2048.model.Direction
import com.componentes.game_2048.model.GameState
import com.componentes.game_2048.model.TileMovement
import com.componentes.game_2048.view.components.CreateGameBoard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {

    private val boardGame = CreateGameBoard()

    private val _gameState = MutableStateFlow(GameState())

    private val movingTile = TileMovement()

    val gameState = _gameState.asStateFlow()

    init {
        startNewGame()
    }

    private fun startNewGame() {
        _gameState.update {
            it.copy(
                board = boardGame.gameBoard(3)
            )
        }
    }

    fun moveTiles(direction: Direction){
        val newBoardGame = movingTile.moveNumbers(_gameState.value.board, direction) ?: return

        _gameState.update {
            it.copy(
                board = newBoardGame
            )
        }

    }

}