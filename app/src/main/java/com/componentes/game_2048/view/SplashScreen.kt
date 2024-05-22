package com.componentes.game_2048.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.componentes.game_2048.view.ui.theme.OuterBox
import kotlinx.coroutines.delay

@Composable
fun splashScreen(navController: NavHostController) {
    LaunchedEffect(key1 = true){
        delay(500)
        navController.navigate(route = Screen.Main.route){
            popUpTo(route = Screen.Main.route){
                inclusive = true
            }
        }
    }

    splashStyle()
}

@Composable
fun splashStyle() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = OuterBox)
    )
}