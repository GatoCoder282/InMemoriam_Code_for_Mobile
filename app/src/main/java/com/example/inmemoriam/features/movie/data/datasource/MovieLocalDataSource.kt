package com.example.inmemoriam.features.movie.data.datasource

import com.example.inmemoriam.features.movie.data.database.dao.IMovieDao
import com.example.inmemoriam.features.movie.data.database.entity.MovieEntity
import com.example.inmemoriam.features.movie.domain.model.MovieModel

class MovieLocalDataSource(private val movieDao: IMovieDao) {
    suspend fun saveLike(movie: MovieModel) {
        movieDao.insertMovie(MovieEntity(movie.title, movie.pathUrl, true))
    }

    suspend fun getMovies(): List<MovieModel> {
        return movieDao.getAllMovies().map {
            MovieModel(it.pathUrl, it.title, it.isLiked)
        }
    }
}
