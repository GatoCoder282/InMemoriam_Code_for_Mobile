package com.example.inmemoriam.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun SnackbarWrapper(
    modifier: Modifier = Modifier,
    snackbarMessage: String?,
    onDismiss: () -> Unit = {},
    content: @Composable (SnackbarHostState) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Muestra el snackbar cuando snackbarMessage cambia
    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = it,
                    actionLabel = "OK"
                )
                onDismiss()
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        androidx.compose.foundation.layout.Box(modifier = Modifier.padding(padding)) {
            content(snackbarHostState)
        }
}}
