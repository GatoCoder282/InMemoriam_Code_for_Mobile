package com.example.inmemoriam.features.movie.data.repository

import com.example.inmemoriam.features.movie.data.datasource.MovieLocalDataSource
import com.example.inmemoriam.features.movie.data.datasource.MovieRemoteDataSource
import com.example.inmemoriam.features.movie.domain.model.MovieModel
import com.example.inmemoriam.features.movie.domain.repository.IMoviesRepository

class MovieRepository(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
): IMoviesRepository {

    override suspend fun fetchPopularMovies(): Result<List<MovieModel>> {
        val remoteResult = movieRemoteDataSource.fetchPopularMovies()
        return remoteResult.map { movies ->
            val likedMovies = movieLocalDataSource.getMovies()
            val likedTitles = likedMovies.filter { it.isLiked }.map { it.title }
            movies.map { it.copy(isLiked = likedTitles.contains(it.title)) }
                .sortedByDescending { it.isLiked } // 🔥 primero los liked
        }
    }

    suspend fun likeMovie(movie: MovieModel) {
        movieLocalDataSource.saveLike(movie.copy(isLiked = true))
    }
}
