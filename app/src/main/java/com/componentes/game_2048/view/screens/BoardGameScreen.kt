package com.componentes.game_2048.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.componentes.game_2048.R
import com.componentes.game_2048.model.Direction
import com.componentes.game_2048.model.GameState
import com.componentes.game_2048.viewmodel.GameViewModel
import com.componentes.game_2048.model.Direction.*
import com.componentes.game_2048.model.GameStatus.*
import com.componentes.game_2048.model.getCellData
import com.componentes.game_2048.utils.DragGestureDirectionDetector
import com.componentes.game_2048.view.components.AppName
import com.componentes.game_2048.view.components.AppNameHeight
import com.componentes.game_2048.view.components.IconButtonComponent
import com.componentes.game_2048.view.components.IconButtonHeight
import com.componentes.game_2048.view.ui.theme.*
import com.componentes.game_2048.view.utils.EMPTY_VALUE

val edge = 5.dp
val inside_padding = 3.dp
val outside_padding = 18.dp
val bottomMarginBoard = 50.dp


@Composable
fun BoardGameScreen(viewModel: GameViewModel, UIState: GameState) {
    var currentDirection by remember { mutableStateOf(NONE) }
    val configuration = LocalConfiguration.current
    val UIBoardSize = configuration.screenWidthDp.dp.minus(outside_padding + outside_padding)
    val reStartButton = outside_padding + AppNameHeight + bottomMarginBoard + UIBoardSize + 15.dp

    val messageToShow = when (UIState.gameStatus) {
        IS_PLAYING -> R.string.is_playing
        GAME_OVER -> R.string.game_over
        PLAYER_WINS -> R.string.player_wins
    }

    Box{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background), // Games background !!!
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = outside_padding,
                        end = outside_padding,
                        top = outside_padding,
                        bottom = bottomMarginBoard
                    ),
                horizontalArrangement = Arrangement.Start
            ) {
                AppName()
            }

            BoardGame(UIState.board, currentDirection, UIBoardSize)

            Spacer(modifier = Modifier.height(IconButtonHeight + 25.dp))
            // Mostrar el puntaje
            Text(
                text = stringResource(R.string.Score) + "${UIState.score}",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier
                    .background(
                        BoxScore,
                        RoundedCornerShape(4.dp)
                    )
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(messageToShow),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight()
            )
        }
    }

    DragGestureDirectionDetector(
        modifier = Modifier.fillMaxSize(),
        onDirectionDetected = {
            currentDirection = it
            viewModel.moveTiles(it)
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButtonComponent(
                modifier = Modifier.offset(y = reStartButton),
                onClick = {
                    viewModel.startNewGame()
                }
            )


        }
    }

}

@Composable
fun showScore(UIState: GameState){
    Text(
        text = "Score: ${UIState.score}",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(top = 8.dp)
    )
}

@Composable
fun GameBoard(gameState: GameState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        for (row in gameState.board) {
            Row {
                for (cell in row) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(Color.Gray)
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = cell.toString())
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Score: ${gameState.score}")
    }
}


@Composable
fun BoardGame(tableData: List<List<Int>>, currentDirection: Direction, UIBoardSize: Dp) {
    LazyColumn(
        modifier = Modifier
            .width(UIBoardSize)
            .height(UIBoardSize)
            .background(
                color = OuterBox,
                shape = RoundedCornerShape(edge)
            )
            .padding(inside_padding)
    ) {
        items(
            items = tableData,
        ) { row ->
            BoardGameRow(rowData = row, UIBoardSize - inside_padding - inside_padding)
        }
    }
}

@Composable
fun BoardGameRow(rowData: List<Int>, UIBoardSize: Dp) {
    val UICellSize = UIBoardSize.div(rowData.size)

    LazyRow(
        modifier = Modifier
            .width(UIBoardSize)
            .height(UICellSize)
    ) {
        items(
            items = rowData,
        ){ cellData ->
            BoardGameCell(cellData, UICellSize)

        }
    }
}

@Composable
fun BoardGameCell(cellNumber: Int, UICellSize: Dp) {
    val cellData = getCellData(cellNumber)
    Box(modifier = Modifier
        .width(UICellSize)
        .height(UICellSize)
        .padding(inside_padding)){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    shape = RoundedCornerShape(edge),
                    color = cellData.backgroundColor
                ),
            contentAlignment = Alignment.Center
        ){
            Text(
                fontSize = 25.sp,
                color = cellData.textColor,
                fontWeight = FontWeight.Bold,
                text = if (cellNumber == EMPTY_VALUE) "" else cellNumber.toString())
        }
    }
}
