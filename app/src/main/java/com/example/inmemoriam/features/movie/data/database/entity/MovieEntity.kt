package com.example.inmemoriam.features.movie.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val title: String,
    val pathUrl: String,
    val isLiked: Boolean
)