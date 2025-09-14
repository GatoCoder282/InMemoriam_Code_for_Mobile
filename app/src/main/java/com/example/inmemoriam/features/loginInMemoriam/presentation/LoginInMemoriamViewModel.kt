package com.example.inmemoriam.features.loginInMemoriam.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inmemoriam.features.loginInMemoriam.domain.model.LoginInMemoriamModel
import com.example.inmemoriam.features.loginInMemoriam.domain.usecase.LoginInMemoriamUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginInMemoriamViewModel(
    private val useCase: LoginInMemoriamUseCase
) : ViewModel() {

    sealed class LoginInMemoriamUiState {
        object Idle : LoginInMemoriamUiState()
        object Loading : LoginInMemoriamUiState()
        data class Success(val data: LoginInMemoriamModel) : LoginInMemoriamUiState()
        data class Error(val message: String) : LoginInMemoriamUiState()
    }

    private val _uiState = MutableStateFlow<LoginInMemoriamUiState>(LoginInMemoriamUiState.Idle)
    val uiState: StateFlow<LoginInMemoriamUiState> = _uiState.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginInMemoriamUiState.Loading
            try {
                val result = useCase.login(username, password)
                if (result.isLoggedIn) {
                    _uiState.value = LoginInMemoriamUiState.Success(result)
                } else {
                    _uiState.value = LoginInMemoriamUiState.Error(result.errorMessage ?: "Login failed")
                }
            } catch (e: Exception) {
                _uiState.value = LoginInMemoriamUiState.Error("Unexpected error: ${e.message}")
            }
        }
    }

    fun contactSupport() {
        viewModelScope.launch {
            val success = useCase.contactSupport()
            if (!success) {
                _uiState.value = LoginInMemoriamUiState.Error("Unable to contact support")
            }
        }
    }

    fun recoverPassword(username: String) {
        viewModelScope.launch {
            val success = useCase.recoverPassword(username)
            if (!success) {
                _uiState.value = LoginInMemoriamUiState.Error("Password recovery failed")
            }
        }
    }
}
