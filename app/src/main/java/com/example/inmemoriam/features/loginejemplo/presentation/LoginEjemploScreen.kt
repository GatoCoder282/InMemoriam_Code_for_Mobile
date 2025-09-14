package com.example.inmemoriam.features.loginejemplo.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.inmemoriam.ui.components.CardScreen
import com.example.inmemoriam.ui.components.SnackbarWrapper
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    vm: LoginEjemploViewModel = koinViewModel(),
    onLoggedIn: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    val state by vm.state.collectAsState()

    val errorMessage = (state as? LoginEjemploViewModel.UiState.Error)?.message

    SnackbarWrapper(
        snackbarMessage = errorMessage,
        onDismiss = { /* Podrías resetear el estado si quieres */ }
    ) {

        CardScreen(
            modifier = modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Column(
                modifier = modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = email, onValueChange = { email = it },
                    label = { Text("Email") }, singleLine = true, modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = pass, onValueChange = { pass = it },
                    label = { Text("Contraseña") }, singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = { vm.login(email, pass) },
                    enabled = state !is LoginEjemploViewModel.UiState.Loading,
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Iniciar sesión") }

                TextButton(
                    onClick = { vm.recover(email) },
                    enabled = state !is LoginEjemploViewModel.UiState.Loading
                ) { Text("¿Olvidaste tu contraseña?") }

                Spacer(Modifier.height(8.dp))
                when (val st = state) {
                    is LoginEjemploViewModel.UiState.Error -> Text(
                        st.message,
                        color = MaterialTheme.colorScheme.error
                    )

                    is LoginEjemploViewModel.UiState.Loading -> CircularProgressIndicator()
                    is LoginEjemploViewModel.UiState.Success -> {
                        Text("¡Bienvenido, ${st.user.name}!")
                        LaunchedEffect(Unit) { onLoggedIn() } // navega tras login
                    }

                    LoginEjemploViewModel.UiState.RecoverySent -> Text("Te enviamos instrucciones al email.")
                    else -> {}
                }
            }
        }
    }
}