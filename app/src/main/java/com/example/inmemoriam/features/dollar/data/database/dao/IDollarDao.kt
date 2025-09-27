package com.example.inmemoriam.features.dollar.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.inmemoriam.features.dollar.data.database.entity.DollarEntity


@Dao
interface IDollarDao {
    @Query("SELECT * FROM dollar")
    suspend fun getList(): List<DollarEntity>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dollar: DollarEntity)


    @Query("DELETE FROM dollar")
    suspend fun deleteAll()


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDollars(lists: List<DollarEntity>)

    @Query("SELECT * FROM dollar ORDER BY timestamp DESC")
    suspend fun getAllOrderedByDate(): List<DollarEntity>


    @Query("DELETE FROM dollar WHERE id = :id")
    suspend fun deleteById(id: Int)
}