package com.example.inmemoriam.features.guestInMemoriam.presentation

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GuestInMemoriamScreen(
    onScanQrClick: () -> Unit,
    onSearchByCode: (String) -> Unit
) {
    val context = LocalContext.current
    var codeInput by remember { mutableStateOf("") }
    var cameraPermissionGranted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome, Guest", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = codeInput,
            onValueChange = { codeInput = it },
            label = { Text("Enter Code") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onSearchByCode(codeInput) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search by Code")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (cameraPermissionGranted) {
                    onScanQrClick()
                } else {
                    // Aquí deberías lanzar el request de permisos desde la Activity
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Scan QR Code")
        }
    }
}
