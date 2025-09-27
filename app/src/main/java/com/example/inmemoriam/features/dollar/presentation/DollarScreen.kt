package com.example.inmemoriam.features.dollar.presentation

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.room.util.copy
import com.example.inmemoriam.features.dollar.domain.model.DollarModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.foundation.lazy.items


@Composable
fun DollarScreen(viewModelDollar: DollarViewModel = koinViewModel()) {
    val state = viewModelDollar.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (val stateValue = state.value) {
            is DollarViewModel.DollarUIState.Error -> Text(stateValue.message)
            DollarViewModel.DollarUIState.Loading -> CircularProgressIndicator()
            is DollarViewModel.DollarUIState.Success -> {
                DollarCard(data = stateValue.data)

                Spacer(modifier = Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                    DollarValueItem("Oficial Compra", stateValue.data.dollarvalue, MaterialTheme.colorScheme.primary)
                    DollarValueItem("Oficial Venta", stateValue.data.dollarvalueParalelo, MaterialTheme.colorScheme.primary)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                    DollarValueItem("Paralelo Compra", stateValue.data.paraleloCompra, MaterialTheme.colorScheme.tertiary)
                    DollarValueItem("Paralelo Venta", stateValue.data.paraleloVenta, MaterialTheme.colorScheme.tertiary)
                }

                Spacer(modifier = Modifier.height(24.dp))

            }
        }
    }
}


@Composable
fun DollarValueItem(title: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = color.copy(alpha = 0.8f)
        )

        Text(
            text = "$$value",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}

@Composable
fun DollarHistoryList(history: List<DollarModel>) {
    if (history.isEmpty()) {
        Text(
            text = "No hay historial disponible",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.padding(16.dp)
        )
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(history) { dollar ->
                DollarHistoryItem(dollar = dollar)
            }
        }
    }
}

@Composable
fun DollarCard(data: DollarModel) {
    androidx.compose.material3.Card(
        modifier = Modifier.padding(16.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("Dólar Oficial", style = MaterialTheme.typography.titleMedium)
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text("Compra: ${data.dollarvalue}")
                Text("Venta: ${data.dollarvalueParalelo}")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Dólar Paralelo", style = MaterialTheme.typography.titleMedium)
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text("Compra: ${data.paraleloCompra}")
                Text("Venta: ${data.paraleloVenta}")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Última actualización: ${java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(data.lastUpdated)}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun DollarHistoryItem(dollar: DollarModel) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = formatDate(dollar.lastUpdated),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Historial con los 4 valores
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = "Oficial: $${dollar.dollarvalue ?: "N/A"}")
                Text(text = "Paralelo: $${dollar.dollarvalueParalelo ?: "N/A"}")
                Text(text = "USDT: $${dollar.paraleloCompra ?: "N/A"}")
                Text(text = "USDC: $${dollar.paraleloVenta ?: "N/A"}")
            }
        }
    }
}


private fun getCurrentTime(): String {
    return SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
}

private fun formatDate(timestamp: Long): String {
    return try {
        SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date(timestamp))
    } catch (e: Exception) {
        "Fecha inválida"
    }
}
