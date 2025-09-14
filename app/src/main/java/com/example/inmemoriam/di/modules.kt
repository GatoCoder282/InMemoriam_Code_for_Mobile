package com.example.inmemoriam.di

import com.example.inmemoriam.features.dollar.data.DollarRepository
import com.example.inmemoriam.features.dollar.domain.repository.IDollarRepository
import com.example.inmemoriam.features.dollar.domain.usecase.FetchDollarUseCase
import com.example.inmemoriam.features.dollar.presentation.DollarViewModel
import com.example.inmemoriam.features.githubEjemplo.data.repository.GithubRepository
import com.example.inmemoriam.features.githubEjemplo.domain.repository.IdGitHubRepository
import com.example.inmemoriam.features.githubEjemplo.domain.usecase.FindPhotobyNickNameUseCase
import com.example.inmemoriam.features.githubEjemplo.presentation.GitHubViewModel
import com.example.inmemoriam.features.guestInMemoriam.data.repository.GuestInMemoriamRepository
import com.example.inmemoriam.features.guestInMemoriam.domain.repository.IGuestInMemoriamRepository
import com.example.inmemoriam.features.guestInMemoriam.domain.usecase.GuestInMemoriamUseCase
import com.example.inmemoriam.features.guestInMemoriam.presentation.GuestInMemoriamViewModel
import com.example.inmemoriam.features.inicioInMemoriam.data.repository.InicioInMemoriamRepository
import com.example.inmemoriam.features.inicioInMemoriam.domain.repository.IInicioInMemoriamRepository
import com.example.inmemoriam.features.inicioInMemoriam.domain.usecase.InicioInMemoriamUseCase
import com.example.inmemoriam.features.inicioInMemoriam.presentation.InicioInMemoriamViewModel
import com.example.inmemoriam.features.loginInMemoriam.data.repository.LoginInMemoriamRepository
import com.example.inmemoriam.features.loginInMemoriam.domain.repository.ILoginInMemoriamRepository
import com.example.inmemoriam.features.loginInMemoriam.domain.usecase.LoginInMemoriamUseCase
import com.example.inmemoriam.features.loginInMemoriam.presentation.LoginInMemoriamViewModel
import com.example.inmemoriam.features.loginejemplo.data.repository.LoginEjemploRepository
import com.example.inmemoriam.features.loginejemplo.domain.repository.ILoginEjemploRepository
import com.example.inmemoriam.features.loginejemplo.domain.usecase.LoginEjemploUseCase
import com.example.inmemoriam.features.loginejemplo.domain.usecase.RecoverPasswordEjemploUsecase
import com.example.inmemoriam.features.loginejemplo.presentation.LoginEjemploViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<IdGitHubRepository>{ GithubRepository() }
    factory { FindPhotobyNickNameUseCase(get()) }
    viewModel { GitHubViewModel(get()) }

    single<ILoginEjemploRepository>{ LoginEjemploRepository() }
    factory { LoginEjemploUseCase(get()) }
    factory { RecoverPasswordEjemploUsecase(get()) }
    viewModel { LoginEjemploViewModel(get(), get()) }


    single<IDollarRepository> { DollarRepository(get()) }
    factory { FetchDollarUseCase(get()) }
    viewModel{ DollarViewModel(get()) }

    single <IInicioInMemoriamRepository>{ InicioInMemoriamRepository() }
    factory { InicioInMemoriamUseCase(get()) }
    viewModel { InicioInMemoriamViewModel(get()) }

    single <ILoginInMemoriamRepository>{ LoginInMemoriamRepository() }
    factory { LoginInMemoriamUseCase(get()) }
    viewModel { LoginInMemoriamViewModel(get()) }

    single <IGuestInMemoriamRepository>{ GuestInMemoriamRepository() }
    factory { GuestInMemoriamUseCase(get()) }
    viewModel { GuestInMemoriamViewModel(get()) }
}