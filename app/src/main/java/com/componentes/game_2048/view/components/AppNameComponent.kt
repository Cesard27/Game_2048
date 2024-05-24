package com.componentes.game_2048.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.componentes.game_2048.R
import com.componentes.game_2048.view.screens.edge
import com.componentes.game_2048.view.ui.theme.InnerBox

val AppNameHeight = 60.dp

@Composable
fun AppName(
    modifier: Modifier = Modifier,
    height: Dp = AppNameHeight,
    verticalPadding: Dp = 10.dp
){
    Box(
        modifier = modifier
            .height(height)
            .background(
                shape = RoundedCornerShape(edge),
                color = InnerBox
            )
    ){
        Text(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            text = stringResource(R.string.app_name),
            modifier = Modifier.padding(vertical = verticalPadding, horizontal = 20.dp),
            color = Color.White
        )
    }
}


