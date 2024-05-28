package com.componentes.game_2048.utils

import android.util.Log
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import com.componentes.game_2048.model.Direction
import com.componentes.game_2048.model.Direction.*

const val DragGesturesTag = "DragGestures"

fun getNewDirection(current: Offset, start: Offset, difference: Float) =
    if (current.x - start.x >= difference) {
        RIGHT
    } else if (start.x - current.x >= difference){
        LEFT
    } else if (current.y - start.y >= difference){
        DOWN
    } else if (start.y - current.y >= difference){
        UP
    } else {
        NONE
    }

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
                    Log.i(DragGesturesTag, "onDragStart: $currentDirection")
                    startPosition = offset
                },
                {
                    Log.i(DragGesturesTag, "onDragStart: $currentDirection")
                    if(!resetAtTheEnd) return@detectDragGestures

                    startPosition = Offset(0f, 0f)
                    currentDirection = NONE
                    onDirectionDetected(currentDirection)
                },
                {
                    Log.i(DragGesturesTag, "onDragStart: $currentDirection")
                    if (!resetAtTheEnd) return@detectDragGestures

                    startPosition = Offset(0f, 0f)
                    currentDirection = NONE
                    onDirectionDetected(currentDirection)
                }
            ){ change, dragAmount ->
                Log.i(DragGesturesTag, "onDrag -> change: $change, dragAmount: $dragAmount")
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