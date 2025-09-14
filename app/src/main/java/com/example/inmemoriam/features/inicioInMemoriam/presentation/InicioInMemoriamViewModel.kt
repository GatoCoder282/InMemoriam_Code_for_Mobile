package com.example.inmemoriam.features.inicioInMemoriam.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inmemoriam.features.inicioInMemoriam.domain.model.InicioInMemoriamModel
import com.example.inmemoriam.features.inicioInMemoriam.domain.usecase.InicioInMemoriamUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InicioInMemoriamViewModel(
    private val useCase: InicioInMemoriamUseCase
) : ViewModel() {

    sealed class InicioInMemoriamUiState{
        object Init: InicioInMemoriamUiState()
        object Loading: InicioInMemoriamUiState()
        class Error(val message: String): InicioInMemoriamUiState()
        class Success(val inicioInMemoriamModel: InicioInMemoriamModel): InicioInMemoriamUiState()
    }
    private val _uiState = MutableStateFlow<InicioInMemoriamUiState>(InicioInMemoriamUiState.Init)
    val uiState: StateFlow<InicioInMemoriamUiState> = _uiState.asStateFlow()

    fun login() {
        viewModelScope.launch {
            _uiState.value = InicioInMemoriamUiState.Loading
            try {
                val result = useCase.login()
                _uiState.value = InicioInMemoriamUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = InicioInMemoriamUiState.Error("Login failed: ${e.message}")
            }
        }
    }

    fun joinAsGuest() {
        viewModelScope.launch {
            _uiState.value = InicioInMemoriamUiState.Loading
            try {
                val result = useCase.joinAsGuest()
                _uiState.value = InicioInMemoriamUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = InicioInMemoriamUiState.Error("Guest access failed: ${e.message}")
            }
        }
    }
}
