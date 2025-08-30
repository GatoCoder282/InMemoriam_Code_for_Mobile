package com.example.inmemoriam.features.githubEjemplo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inmemoriam.features.githubEjemplo.domain.model.UserModel
import com.example.inmemoriam.features.githubEjemplo.domain.usecase.FindPhotobyNickNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GitHubViewModel(
    val usecase: FindPhotobyNickNameUseCase
): ViewModel() {
    sealed class GithubStateUI {
        object Init: GithubStateUI()
        object Loading: GithubStateUI()
        class Error(val message: String): GithubStateUI()
        class Success(val github: UserModel): GithubStateUI()
    }
    private val _state = MutableStateFlow<GithubStateUI>(GithubStateUI.Init)

    val state : StateFlow<GithubStateUI> = _state.asStateFlow()

    fun fetchAlias(nickname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = GithubStateUI.Loading
            val result = usecase.invoke(nickname)

            result.fold(
                onSuccess = {
                        user -> _state.value = GithubStateUI.Success( user )
                },
                onFailure = {
                        error -> _state.value = GithubStateUI.Error(message = error.message ?: "Error desconocido")
                }
            )

//            when {
//                result.isSuccess -> {
//                    val user = result.getOrNull()
//                    _state.value = GithubStateUI.Success( user!! )
//                }
//                result.isFailure -> {
//                    _state.value = GithubStateUI.Error(message = "Error")
//                }
//            }
        }
    }

}