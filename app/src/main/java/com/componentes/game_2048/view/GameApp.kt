package com.componentes.game_2048.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.componentes.game_2048.view.screens.BoardGameScreen
import com.componentes.game_2048.viewmodel.GameViewModel

@Composable
fun GameApp() {
    val viewModel: GameViewModel = viewModel()
    val UIState by viewModel.gameState.collectAsState()

    BoardGameScreen(viewModel, UIState)
}