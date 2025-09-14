package com.example.inmemoriam.features.guestInMemoriam.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inmemoriam.features.guestInMemoriam.domain.model.GuestInMemoriamModel
import com.example.inmemoriam.features.guestInMemoriam.domain.usecase.GuestInMemoriamUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GuestInMemoriamViewModel(
    private val useCase: GuestInMemoriamUseCase
) : ViewModel() {

    sealed class GuestInMemoriamUiState {
        object Idle : GuestInMemoriamUiState()
        object Loading : GuestInMemoriamUiState()
        data class Success(val data: GuestInMemoriamModel) : GuestInMemoriamUiState()
        data class Error(val message: String) : GuestInMemoriamUiState()
    }

    private val _uiState = MutableStateFlow<GuestInMemoriamUiState>(GuestInMemoriamUiState.Idle)
    val uiState: StateFlow<GuestInMemoriamUiState> = _uiState

    fun searchByCode(code: String) {
        viewModelScope.launch {
            _uiState.value = GuestInMemoriamUiState.Loading
            try {
                val result = useCase.searchByCode(code)
                _uiState.value = GuestInMemoriamUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = GuestInMemoriamUiState.Error("Search failed: ${e.message}")
            }
        }
    }

    fun processQrResult(qrContent: String) {
        viewModelScope.launch {
            _uiState.value = GuestInMemoriamUiState.Loading
            try {
                val result = useCase.processQr(qrContent)
                _uiState.value = GuestInMemoriamUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = GuestInMemoriamUiState.Error("QR processing failed: ${e.message}")
            }
        }
    }
}
