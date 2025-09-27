package com.example.inmemoriam.features.dollar.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inmemoriam.features.dollar.data.datasource.DollarLocalDataSource
import com.example.inmemoriam.features.dollar.domain.model.DollarModel
import com.example.inmemoriam.features.dollar.domain.usecase.FetchDollarUseCase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class DollarViewModel(
    private val fetchDollarUseCase: FetchDollarUseCase,
    private val localDataSource: DollarLocalDataSource
) : ViewModel() {

    sealed class DollarUIState {
        object Loading : DollarUIState()
        class Error(val message: String) : DollarUIState()
        class Success(val data: DollarModel, val history: List<DollarModel>) : DollarUIState()
    }

    private val _uiState = MutableStateFlow<DollarUIState>(DollarUIState.Loading)
    val uiState: StateFlow<DollarUIState> = _uiState.asStateFlow()

    init {
        getDollar()
        loadHistory()
    }

    fun getDollar() {
        viewModelScope.launch(Dispatchers.IO) {
            getToken()
            try {
                fetchDollarUseCase.invoke().collect { data ->
                    val history = withContext(Dispatchers.IO) { localDataSource.getAllOrderedByDate() }
                    _uiState.value = DollarUIState.Success(data, history)
                }
            } catch (e: Exception) {
                _uiState.value = DollarUIState.Error("Error al obtener dólar: ${e.message}")
            }
        }
    }

    suspend fun getToken(): String = suspendCoroutine { continuation ->
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("FIREBASE", "getInstanceId failed", task.exception)
                    continuation.resumeWithException(task.exception ?: Exception("Unknown error"))
                    return@addOnCompleteListener
                }
                continuation.resume(task.result ?: "")
            }
    }

    fun loadHistory() {
        viewModelScope.launch {
            try {
                val history = withContext(Dispatchers.IO) {
                    localDataSource.getAllOrderedByDate()
                }
                val currentData = (_uiState.value as? DollarUIState.Success)?.data
                    ?: DollarModel("0", "0", "0", "0")
                _uiState.value = DollarUIState.Success(currentData, history)
            } catch (e: Exception) {
                Log.e("DOLLAR_VM", "Error Room getAll", e)
                val currentData = (_uiState.value as? DollarUIState.Success)?.data
                    ?: DollarModel("0", "0", "0", "0")
                _uiState.value = DollarUIState.Success(currentData, emptyList())
            }
        }
    }
}
