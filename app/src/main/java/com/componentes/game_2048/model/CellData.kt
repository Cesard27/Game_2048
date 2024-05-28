package com.componentes.game_2048.model

import androidx.compose.ui.graphics.Color
import com.componentes.game_2048.view.ui.theme.*
import com.componentes.game_2048.view.utils.EMPTY_VALUE


fun getCellData(cellData: Int): CellData{
    val backgroundColor = when (cellData) {
        EMPTY_VALUE -> InnerBox
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

    val cellTextColor = when(cellData) {
        2, 4, 32-> DarkText
        64, 512, 1024 -> DarkText
        8, 16, 256, 128 -> LightText
        128, 2048, 4096 -> LightText


        else -> DarkText
    }

    return CellData(
        backgroundColor = backgroundColor,
        textColor = cellTextColor
    )
}


data class CellData(
    val backgroundColor: Color,
    val textColor: Color
)