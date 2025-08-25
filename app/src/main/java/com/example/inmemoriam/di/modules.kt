package com.example.inmemoriam.di

import com.example.inmemoriam.data.repository.GithubRepository
import com.example.inmemoriam.domain.respository.IdGitHubRepository
import com.example.inmemoriam.domain.usecase.FindPhotobyNickNameUseCase
import com.example.inmemoriam.presentation.GitHubViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<IdGitHubRepository>{ GithubRepository() }
    factory { FindPhotobyNickNameUseCase(get()) }
    viewModel { GitHubViewModel(get()) }
}