package com.componentes.game_2048.viewmodel

import androidx.lifecycle.ViewModel
import com.componentes.game_2048.model.Direction
import com.componentes.game_2048.model.Direction.*
import com.componentes.game_2048.model.GameState
import com.componentes.game_2048.model.GameStatus.*
import com.componentes.game_2048.model.persistence.FirestoreUtil
import com.componentes.game_2048.view.utils.CreateGameBoard
import com.componentes.game_2048.view.utils.TileMovement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

//const val WINNING_NUMBER = 2048
const val WINNING_NUMBER = 64

@HiltViewModel
class GameViewModel @Inject constructor(
    private val boardGame: CreateGameBoard,
    private val movingTile: TileMovement
): ViewModel() {

    private val _gameState = MutableStateFlow(GameState())
    val gameState = _gameState.asStateFlow()

    val score
        get() = gameState.value.score

    init {
        startNewGame()
    }

    fun startNewGame() {
        _gameState.update {
            it.copy(
                board = boardGame.gameBoard(4),
                gameStatus = IS_PLAYING,
                winningNumber = WINNING_NUMBER,
                score = 0
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
                winningNumber = newBoard.winningNumber,
                score = it.score + calcularPuntaje(board, newBoard.boardGame)
            )
        }
    }


    private fun calcularPuntaje(oldBoard: List<List<Int>>, newBoard: List<List<Int>>): Int {
        var puntaje = 0
        for (i in oldBoard.indices) {
            for (j in oldBoard[i].indices) {
                if (oldBoard[i][j] != newBoard[i][j]) {
                    val fichaAntigua = oldBoard[i][j]
                    val fichaNueva = newBoard[i][j]
                    if (fichaAntigua != 0 && fichaNueva != 0 && fichaNueva % 2 == 0 && fichaNueva == fichaAntigua * 2) {
                        // Se ha combinado una ficha, sumar su valor al puntaje
                        puntaje += fichaNueva
                    }
                }
            }
        }
        return puntaje
    }

    fun saveGameState(gameState: GameState) {
        FirestoreUtil.saveGameState(gameState)
    }

    fun loadGameState() {
        FirestoreUtil.loadGameState(
            onSuccess = { gameState ->
                // Actualizar el estado del juego en la ViewModel
                // gameStateLiveData.value = gameState
            },
            onFailure = { exception ->
                // Manejar el error
            }
        )
    }

}
