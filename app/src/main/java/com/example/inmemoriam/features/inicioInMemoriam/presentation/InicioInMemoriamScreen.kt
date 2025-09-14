package com.example.inmemoriam.features.inicioInMemoriam.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inmemoriam.features.inicioInMemoriam.domain.model.InicioInMemoriamModel

@Composable
fun InicioInMemoriamScreen(
    model: InicioInMemoriamModel,
    onLoginClick: () -> Unit,
    onGuestClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "In Memoriam",
            fontSize = 32.sp,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(48.dp))

        if (model.isLoggedIn.not()) {
            Button(
                onClick = onLoginClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Log In")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onGuestClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Join as Guest")
            }
        } else {
            Text("Welcome back!")
        }
    }
}
