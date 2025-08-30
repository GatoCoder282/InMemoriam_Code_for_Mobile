package com.example.inmemoriam.di

import com.example.inmemoriam.features.githubEjemplo.data.repository.GithubRepository
import com.example.inmemoriam.features.githubEjemplo.domain.repository.IdGitHubRepository
import com.example.inmemoriam.features.githubEjemplo.domain.usecase.FindPhotobyNickNameUseCase
import com.example.inmemoriam.features.githubEjemplo.presentation.GitHubViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<IdGitHubRepository>{ GithubRepository() }
    factory { FindPhotobyNickNameUseCase(get()) }
    viewModel { GitHubViewModel(get()) }
}