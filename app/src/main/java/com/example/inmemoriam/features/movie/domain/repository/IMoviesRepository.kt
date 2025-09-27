package com.example.inmemoriam.features.movie.domain.repository

import com.example.inmemoriam.features.movie.domain.model.MovieModel

interface IMoviesRepository {
    suspend fun fetchPopularMovies(): Result<List<MovieModel>>
}