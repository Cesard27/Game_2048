package com.componentes.game_2048.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.componentes.game_2048.model.Direction
import com.componentes.game_2048.model.GameState
import com.componentes.game_2048.viewmodel.GameViewModel
import com.componentes.game_2048.model.Direction.*
import com.componentes.game_2048.utils.DragGestureDirectionDetector
import com.componentes.game_2048.view.components.AppName
import com.componentes.game_2048.view.components.AppNameHeight
import com.componentes.game_2048.view.components.IconButtonHeight
import com.componentes.game_2048.view.ui.theme.*
import com.componentes.game_2048.view.utils.DEFAULT_VALUE

val edge = 5.dp
val inside_padding = 3.dp
val outside_padding = 18.dp
val bottomMarginBoard = 50.dp


@Composable
fun BoardGameScreen(viewModel: GameViewModel, UIState: GameState) {
    var currentDirection by remember { mutableStateOf(NONE) }
    val configuration = LocalConfiguration.current
    val UIBoardSize = configuration.screenWidthDp.dp.minus(outside_padding + outside_padding)
    val reStartButton = outside_padding + AppNameHeight + bottomMarginBoard + UIBoardSize + 10.dp

    Box{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(InnerBox),
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
            ){
                AppName()
            }
        }

        BoardGame(UIState.board, currentDirection, UIBoardSize)
        Spacer(modifier = Modifier.height(IconButtonHeight + 10.dp))
        Text(
            text = "this is a message",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight()
        )
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
            IconButton(
                modifier = Modifier.offset(y = reStartButton),
                onClick = {

                }
            ){}
        }
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
                    color = InnerBox
                ),
            contentAlignment = Alignment.Center
        ){
            Text(
                fontSize = 25.sp,
                color = DarkText,
                fontWeight = FontWeight.Bold,
                text = if (cellNumber == DEFAULT_VALUE) "" else cellNumber.toString())
        }
    }
}

fun getCellData(cellData: Int): CellData{
    val backgroundColor = when (cellData) {
        DEFAULT_VALUE -> InnerBox
        2 -> Box2
        4 -> Box4
        8 -> Box8
        16 -> Box16
        32 -> Box32
        64 -> Box64
        128 -> Box128

        256 -> Box256
        512 -> Box512
        1024 -> Box1024
        2048 -> Box2048
        4096 -> Box4096

        else -> Color.Green
    }

    val textColor = when(cellData) {
        2, 4, 8, 16 -> DarkText
        32, 64, 128 -> LightText

        256, 512, 1024, 2048 -> DarkText
        4096, 8192 -> LightText

        else -> DarkText
    }

    return CellData(
        backgroundColor = backgroundColor,
        textColor = textColor
    )
}

data class CellData(
    val backgroundColor: Color,
    val textColor: Color
)