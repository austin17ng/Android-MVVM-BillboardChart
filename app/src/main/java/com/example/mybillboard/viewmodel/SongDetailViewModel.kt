package com.example.mybillboard.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.mybillboard.database.BillboardDatabase
import com.example.mybillboard.domain.Song
import com.example.mybillboard.network.BillboardApi
import com.example.mybillboard.network.asDomainModel
import com.example.mybillboard.repository.BillboardRepository
import kotlinx.coroutines.launch

class SongDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val billboardRepository = BillboardRepository(BillboardDatabase.getInstance(application.applicationContext))

    private val _song = MutableLiveData<Song>()
    val song: LiveData<Song>
        get() = _song

    private val _navigateToSpotify = MutableLiveData<String?>()
    val navigateToSpotify: LiveData<String?>
        get() = _navigateToSpotify

    private val _navigateToMessenger = MutableLiveData<String?>()
    val navigateToMessenger: LiveData<String?>
        get() = _navigateToMessenger


//    fun getSong(songId: String) = billboardRepository.getSongBy(songId)
//
//    fun refreshSong(songId: String) {
//        viewModelScope.launch {
//            billboardRepository.refreshSongById(songId)
//        }
//    }

    fun fetchSong(songId: String) {
        viewModelScope.launch {
            try {
                _song.value = BillboardApi.retrofitService.getSong(songId).asDomainModel()
            } catch (e: Exception) {

            }
        }
    }

    fun openSpotify() {
        _navigateToSpotify.value = _song.value!!.spotifyUri
    }

    fun openSpotifyDone() {
        _navigateToSpotify.value = null
    }

    fun openMessenger() {
        _navigateToMessenger.value = "https://www.youtube.com/watch?v=${_song.value?.youtubeId}"
    }

    fun openMessengerDone() {
        _navigateToMessenger.value = null
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SongDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SongDetailViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}