package com.example.inmemoriam.features.movie.domain.model

data class MovieModel(
    val pathUrl: String,
    val title: String,
    val isLiked: Boolean = false
)