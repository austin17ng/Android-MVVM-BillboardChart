package com.example.mybillboard.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.mybillboard.database.BillboardDatabase
import com.example.mybillboard.database.asDomainModel
import com.example.mybillboard.domain.ChartItem
import com.example.mybillboard.domain.Song
import com.example.mybillboard.network.BillboardApi
import com.example.mybillboard.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BillboardRepository(private val database: BillboardDatabase) {
    val chartItems: LiveData<List<ChartItem>> = Transformations.map(
        database.chartItem().getAll()
    ) {
        it.asDomainModel()
    }

    fun getSongBy(id: String): LiveData<Song> {
        return Transformations.map(
            database.song().getSongById(id)
        ) {
            it.asDomainModel()
        }
    }

    suspend fun refreshChart() {
        withContext(Dispatchers.IO) {
            val items = BillboardApi.retrofitService.getHot100()
            database.chartItem().insertAll(items.asDatabaseModel())
        }
    }

    suspend fun refreshSongById(id: String) {
        withContext(Dispatchers.IO) {
            try {
                Log.d("xxx", "refresh song $id")
                val song = BillboardApi.retrofitService.getSong(id)
                database.song().insert(song.asDatabaseModel())
            } catch (e: Exception) {

            }
        }
    }
}