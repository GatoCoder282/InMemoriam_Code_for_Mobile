package com.example.inmemoriam.features.loginejemplo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inmemoriam.features.loginejemplo.domain.model.LoginEjemploModel
import com.example.inmemoriam.features.loginejemplo.domain.usecase.LoginEjemploUseCase
import com.example.inmemoriam.features.loginejemplo.domain.usecase.RecoverPasswordEjemploUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginEjemploViewModel(
    private val loginUseCase: LoginEjemploUseCase,
    private val recoverUseCase: RecoverPasswordEjemploUsecase
) : ViewModel() {

    sealed class UiState {
        object Idle : UiState()
        object Loading : UiState()
        data class Error(val message: String) : UiState()
        data class Success(val user: LoginEjemploModel) : UiState()
        object RecoverySent : UiState()
    }

    private val _state = MutableStateFlow<UiState>(UiState.Idle)
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = UiState.Loading
            val result = loginUseCase.invoke(email, password)
            result.fold(
                onSuccess = { _state.value = UiState.Success(it) },
                onFailure = { _state.value = UiState.Error(it.message ?: "Error") }
            )
        }
    }

    fun recover(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = UiState.Loading
            val result = recoverUseCase.invoke(email)
            result.fold(
                onSuccess = { _state.value = UiState.RecoverySent },
                onFailure = { _state.value = UiState.Error(it.message ?: "Error") }
            )
        }
    }
}