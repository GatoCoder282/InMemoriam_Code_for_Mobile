package com.example.inmemoriam.features.dollar.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dollar")
data class DollarEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "dollarvalue")
    var dollarvalue: String ,

    @ColumnInfo(name = "dollarvalueParalelo")
    var dollarvalueParalelo: String ,

    @ColumnInfo(name = "timestamp")
    var timestamp: Long = 0)