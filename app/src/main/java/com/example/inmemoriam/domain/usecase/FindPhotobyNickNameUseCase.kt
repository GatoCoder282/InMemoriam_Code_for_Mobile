package com.example.inmemoriam.domain.usecase

import com.example.inmemoriam.domain.model.UserModel
import com.example.inmemoriam.domain.respository.IdGitHubRepository
import kotlinx.coroutines.delay

class FindPhotobyNickNameUseCase(
    val repository: IdGitHubRepository
) {
    suspend fun invoke(nickname: String) : Result<UserModel>{
        delay(5000)
        return repository.findByNick(value = nickname)
        }
}