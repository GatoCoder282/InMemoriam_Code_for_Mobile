package com.example.inmemoriam.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FlowRowWrapper(
    modifier: Modifier = Modifier,
    horizontalGap: Int = 8,
    verticalGap: Int = 8,
    content: @Composable () -> Unit
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(horizontalGap.dp),
        verticalArrangement = Arrangement.spacedBy(verticalGap.dp),
        content = { content() }
    )
}
