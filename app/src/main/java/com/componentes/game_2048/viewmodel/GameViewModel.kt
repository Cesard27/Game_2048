package com.componentes.game_2048.viewmodel

import androidx.lifecycle.ViewModel
import com.componentes.game_2048.model.Direction
import com.componentes.game_2048.model.Direction.*
import com.componentes.game_2048.model.GameState
import com.componentes.game_2048.model.GameStatus.*
import com.componentes.game_2048.view.utils.CreateGameBoard
import com.componentes.game_2048.view.utils.TileMovement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

const val WINNING_NUMBER = 2048

@HiltViewModel
class GameViewModel @Inject constructor(
    private val boardGame: CreateGameBoard,
    private val movingTile: TileMovement
): ViewModel() {

    private val _gameState = MutableStateFlow(GameState())
    val gameState = _gameState.asStateFlow()

    init {
        startNewGame()
    }

    fun startNewGame() {
        _gameState.update {
            it.copy(
                board = boardGame.gameBoard(4),
                gameStatus = IS_PLAYING,
                winningNumber = WINNING_NUMBER
            )
        }
    }

    fun moveTiles(direction: Direction) = with(_gameState.value){

        if (direction == NONE) return@with

        val newBoard = movingTile.moveNumbers(board, direction, gameStatus, winningNumber)

        _gameState.update {
            it.copy(
                board = newBoard.boardGame,
                gameStatus = newBoard.gameStatus,
                winningNumber = newBoard.winningNumber
            )
        }

    }

}