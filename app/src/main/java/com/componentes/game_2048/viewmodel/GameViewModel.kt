package com.componentes.game_2048.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.componentes.game_2048.model.Direction
import com.componentes.game_2048.model.GameState
import com.componentes.game_2048.view.utils.CreateGameBoard
import com.componentes.game_2048.view.utils.MoveNumberResult
import com.componentes.game_2048.view.utils.TileMovement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

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

    private fun startNewGame() {
        _gameState.update {
            it.copy(
                board = boardGame.gameBoard(4),
                isGameOver = false
            )
        }
    }

    fun moveTiles(direction: Direction) = with(_gameState.value){
        val newBoard = movingTile.moveNumbers(board, direction, isGameOver)

        _gameState.update {
            it.copy(
                board = newBoard.boardGame,
                isGameOver = newBoard.isGameOver
            )
        }

    }

}