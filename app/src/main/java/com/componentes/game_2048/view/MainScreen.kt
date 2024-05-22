package com.componentes.game_2048.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.componentes.game_2048.model.Direction
import com.componentes.game_2048.model.Direction.*
import com.componentes.game_2048.view.components.DEFAULT_VALUE
import com.componentes.game_2048.view.ui.theme.Background
import com.componentes.game_2048.view.ui.theme.Box2
import com.componentes.game_2048.view.ui.theme.InnerBox
import com.componentes.game_2048.view.ui.theme.OuterBox
import com.componentes.game_2048.viewmodel.GameViewModel

@Composable
fun mainScreen(){


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Background
    ) {
        GameStart()
    }


}

@Composable
fun GameStart() {
    var currentDirection by remember { mutableStateOf(NONE) }
    val viewModel: GameViewModel = viewModel()
    val UIState by viewModel.gameState.collectAsState()

    Box{
        Column(
            modifier = Modifier.fillMaxSize().background(Background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Table(UIState.board, currentDirection)
        }

        DragGestureDirectionDetector(
            modifier = Modifier.fillMaxSize(),
            onDirectionDetected = {
                currentDirection = it
                viewModel.moveTiles(it)
            }
        ){}
    }
}

@Composable
fun Table( tableData: List<List<Int>>, direction: Direction) {
    LazyColumn( modifier = Modifier.background(InnerBox)
    ) {
        items(
            items = tableData,
        ){ row ->
            TableRow(rowData = row)
        }
    }
}

@Composable
fun TableRow(rowData: List<Int>) {
    LazyRow {
        items(items = rowData){
            cellData ->
            TableCell(cellData)
        }
    }
}

@Composable
fun TableCell(cellData: Int){
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .background(Box2)
            .border(3.dp, OuterBox)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ){
        Text(if(cellData == DEFAULT_VALUE) "" else cellData.toString())
    }
}

fun getNewDirection(current: Offset, start: Offset, difference: Float) =
    if (current.x - start.x >= difference) {
        RIGHT
    } else if (start.x - current.x >= difference){
        LEFT
    } else if (current.y - start.y >= difference){
        DOWN
    } else if (start.y - current.y >= difference){
        UP
    } else { NONE }

@SuppressLint("SuspiciousIndentation")
@Composable
fun DragGestureDirectionDetector(
    modifier: Modifier = Modifier,
    resetAtTheEnd: Boolean = true,
    difference: Float = 40f,
    onDirectionDetected: (Direction) -> Unit,
    content: @Composable () -> Unit
){
    var startPosition by remember { mutableStateOf(Offset(0f, 0f)) }
    var currentDirection = NONE

        Box(
            modifier = Modifier
                .pointerInput(Unit) {
                    detectDragGestures(
                        { offset ->
                            startPosition = offset
                        },
                        {
                            if(!resetAtTheEnd) return@detectDragGestures

                            startPosition = Offset(0f, 0f)
                            currentDirection = NONE
                            onDirectionDetected(currentDirection)
                        },
                        {
                            if (!resetAtTheEnd) return@detectDragGestures

                            startPosition = Offset(0f, 0f)
                            currentDirection = NONE
                            onDirectionDetected(currentDirection)
                        }
                    ){ change, dragAmount ->
                        if (currentDirection != NONE) return@detectDragGestures

                        val currentPosition = change.position

                        val newDirection = getNewDirection(currentPosition, startPosition, difference)
                        if (newDirection == NONE) return@detectDragGestures

                        currentDirection = newDirection
                        onDirectionDetected(currentDirection)
                    }
                }
        ){
            content()
        }
}