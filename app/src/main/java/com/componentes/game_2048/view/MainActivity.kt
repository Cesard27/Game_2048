package com.componentes.game_2048.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.componentes.game_2048.view.ui.theme.Game_2048Theme
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Game_2048Theme {
                navController = rememberNavController()
                SetupNavigation(navController = navController)
            }
        }
    }
}

@Composable
fun SetupNavigation( navController: NavHostController ){

    NavHost(navController = navController, startDestination = Screen.Splash.route){
        composable(route = Screen.Main.route){
            mainScreen()
        }
        composable(route = Screen.Splash.route){
            splashScreen(navController = navController)
        }
    }
}


sealed class Screen(val route: String) {
    object Main: Screen("main_screen")
    object Splash: Screen("splash_screen")
}