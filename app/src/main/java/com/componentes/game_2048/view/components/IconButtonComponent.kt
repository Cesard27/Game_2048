package com.componentes.game_2048.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.componentes.game_2048.view.screens.edge
import com.componentes.game_2048.view.ui.theme.DarkText
import com.componentes.game_2048.view.ui.theme.InnerBox
import com.componentes.game_2048.view.ui.theme.LightText
import com.componentes.game_2048.view.ui.theme.OuterBox

val IconButtonHeight = 70.dp

@Composable
fun IconButtonComponent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(IconButtonHeight)
            .width(IconButtonHeight),
        shape = RoundedCornerShape(edge),
        colors = ButtonDefaults.buttonColors(
            contentColor = LightText,
            containerColor = InnerBox,
            disabledContentColor = OuterBox,
            disabledContainerColor = DarkText
        )
    ){
        Icon(
            Icons.Rounded.Refresh,
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}