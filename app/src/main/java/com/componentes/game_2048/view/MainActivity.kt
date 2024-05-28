package com.componentes.game_2048.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.componentes.game_2048.view.ui.theme.*
import com.componentes.game_2048.viewmodel.GameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var viewModel: GameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)



        setContent {
            Game_2048Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = Background) {
                    GameApp()
                }
            }
        }


        // Cargar el estado del juego al iniciar la actividad
        //viewModel.loadGameState()
    }


    override fun onStop() {
        super.onStop()
        viewModel.saveGameState(viewModel.gameState.value)
    }
}
