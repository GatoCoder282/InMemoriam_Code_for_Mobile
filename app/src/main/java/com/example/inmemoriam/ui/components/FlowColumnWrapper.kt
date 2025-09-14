package com.example.inmemoriam.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FlowColumnWrapper(
    modifier: Modifier = Modifier,
    verticalGap: Int = 8,
    horizontalGap: Int = 8,
    content: @Composable () -> Unit
) {
    FlowColumn(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(verticalGap.dp),
        horizontalArrangement = Arrangement.spacedBy(horizontalGap.dp),
        content = { content() }
    )
}
