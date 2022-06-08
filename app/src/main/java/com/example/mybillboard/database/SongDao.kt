package com.example.mybillboard.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(song: DatabaseSong)

    @Query("SELECT * FROM song WHERE id = :songId LIMIT 1")
    fun getSongById(songId: String) : LiveData<DatabaseSong>
}