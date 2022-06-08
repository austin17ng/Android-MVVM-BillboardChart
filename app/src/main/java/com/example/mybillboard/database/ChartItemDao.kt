package com.example.mybillboard.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChartItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<DatabaseChartItem>)


    @Query("SELECT * FROM chartItem")
    fun getAll(): LiveData<List<DatabaseChartItem>>
}