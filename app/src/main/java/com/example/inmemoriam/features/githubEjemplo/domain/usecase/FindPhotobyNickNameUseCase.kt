package com.example.inmemoriam.features.githubEjemplo.domain.usecase

import com.example.inmemoriam.features.githubEjemplo.domain.model.UserModel
import com.example.inmemoriam.features.githubEjemplo.domain.repository.IdGitHubRepository
import kotlinx.coroutines.delay

class FindPhotobyNickNameUseCase(
    val repository: IdGitHubRepository
) {
    suspend fun invoke(nickname: String) : Result<UserModel>{
        delay(5000)
        return repository.findByNick(value = nickname)
        }
}