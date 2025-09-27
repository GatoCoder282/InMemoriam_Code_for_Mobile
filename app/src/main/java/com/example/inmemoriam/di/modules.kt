package com.example.inmemoriam.di

import com.example.inmemoriam.features.dollar.data.database.AppRoomDatabase
import retrofit2.converter.gson.GsonConverterFactory
import com.example.inmemoriam.features.dollar.data.datasource.DollarLocalDataSource
import com.example.inmemoriam.features.dollar.data.datasource.RealTimeRemoteDataSource
import com.example.inmemoriam.features.dollar.data.repository.DollarRepository
import com.example.inmemoriam.features.dollar.domain.repository.IDollarRepository
import com.example.inmemoriam.features.dollar.domain.usecase.FetchDollarUseCase
import com.example.inmemoriam.features.dollar.presentation.DollarHistoryViewModel
import com.example.inmemoriam.features.dollar.presentation.DollarViewModel
import com.example.inmemoriam.features.githubEjemplo.data.api.GithubService
import com.example.inmemoriam.features.githubEjemplo.data.datasource.GithubRemoteDataSource
import com.example.inmemoriam.features.githubEjemplo.data.repository.GithubRepository
import com.example.inmemoriam.features.githubEjemplo.domain.repository.IGithubRepository
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
import com.example.inmemoriam.features.movie.data.api.MovieService
import com.example.inmemoriam.features.movie.data.database.MovieRoomDatabase
import com.example.inmemoriam.features.movie.data.datasource.MovieLocalDataSource
import com.example.inmemoriam.features.movie.data.datasource.MovieRemoteDataSource
import com.example.inmemoriam.features.movie.data.repository.MovieRepository
import com.example.inmemoriam.features.movie.domain.usecase.FetchPopularMoviesUseCase
import com.example.inmemoriam.features.movie.presentation.PopularMoviesViewModel
import com.example.inmemoriam.features.profile.application.ProfileViewModel
import com.example.inmemoriam.features.profile.data.repository.ProfileRepository
import com.example.inmemoriam.features.profile.domain.repository.IProfileRepository
import com.example.inmemoriam.features.profile.domain.usecase.GetProfileUseCase
import com.google.firebase.database.FirebaseDatabase
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val appModule = module {

    // OkHttpClient
    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // Retrofit
    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // GithubService
    single<GithubService> {
        get<Retrofit>().create(GithubService::class.java)
    }

    // MovieDB Service (base URL diferente)
    single<MovieService> {
        get<Retrofit>().newBuilder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()) // 👈 necesario
            .build()
            .create(MovieService::class.java)
    }

    // Firebase Realtime Database
    single { FirebaseDatabase.getInstance() }


    // DataSources
    single { RealTimeRemoteDataSource() }
    single { DollarLocalDataSource(get()) }
    single { GithubRemoteDataSource(get()) }
    single { MovieRemoteDataSource(get(), get()) }
    single { MovieLocalDataSource(get()) }

    single {com.example.inmemoriam.features.movie.data.datasource.MovieLocalDataSource(get())}

    // Database
    single { AppRoomDatabase.getDatabase(get()) }
    single { get<AppRoomDatabase>().dollarDao() }
    single { get<MovieRoomDatabase>().MovieDao() }
    single { MovieRoomDatabase.getDatabase(get()) }


    // Repositories
    single<IDollarRepository> { DollarRepository(get(), get()) }
    single<IProfileRepository> { ProfileRepository() }
    single<IGithubRepository> { GithubRepository(get()) }
    single<MovieRepository> { MovieRepository(get(),get()) }

    // UseCases
    factory { FetchDollarUseCase(get()) }
    factory { GetProfileUseCase(get()) }
    factory { FindPhotobyNickNameUseCase(get()) }
    factory { FetchPopularMoviesUseCase(get()) }

    // ViewModels
    viewModel { ProfileViewModel(get()) }
    viewModel { DollarViewModel(get(), get()) }
    viewModel { GitHubViewModel(get(), get()) }
    viewModel { PopularMoviesViewModel(get(), get()) }
    viewModel { DollarHistoryViewModel(get()) }



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