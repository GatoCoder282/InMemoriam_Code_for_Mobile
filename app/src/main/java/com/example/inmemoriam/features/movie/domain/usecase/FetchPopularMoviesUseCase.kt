package com.example.inmemoriam.features.movie.domain.usecase

import com.example.inmemoriam.features.movie.domain.model.MovieModel
import com.example.inmemoriam.features.movie.domain.repository.IMoviesRepository

class FetchPopularMoviesUseCase(
    private val movieRepository: IMoviesRepository
) {
    suspend fun invoke(): Result<List<MovieModel>> {
        return movieRepository.fetchPopularMovies()
    }
}