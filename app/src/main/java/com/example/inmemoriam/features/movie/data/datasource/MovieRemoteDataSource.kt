package com.example.inmemoriam.features.movie.data.datasource

import com.example.inmemoriam.features.movie.data.api.MovieService
import com.example.inmemoriam.features.movie.data.api.dto.MoviePageDto
import com.example.inmemoriam.features.movie.domain.model.MovieModel
import com.google.android.gms.common.api.Response


class MovieRemoteDataSource(
    private val movieServie: MovieService,
    private val apiKey: String
) {
    suspend fun fetchPopularMovies(): Result<List<MovieModel>> {
        val response = movieServie.fetchPopularMovies(apiKey = apiKey)
        return if (response.isSuccessful) {
            val moviePage = response.body()
            if (moviePage != null) {
                return Result.success(moviePage.results.map { dto ->  MovieModel("https://image.tmdb.org/t/p/w185"+dto.pathUrl, dto.title) } )
            }
            Result.success(emptyList())
        } else {
            Result.failure(Exception("Error"))
        }
    }
}