package com.example.mybillboard.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.mybillboard.database.BillboardDatabase
import com.example.mybillboard.repository.BillboardRepository
import kotlinx.coroutines.launch

class Hot100ViewModel(application: Application) : AndroidViewModel(application) {
    private val billboardRepository =
        BillboardRepository(BillboardDatabase.getInstance(application.applicationContext))

    val items = billboardRepository.chartItems

    private val _isNetworkError = MutableLiveData<Boolean>(false)
    val isNetworkError: LiveData<Boolean>
        get() = _isNetworkError

    private val _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    init {
        refreshDataFromRepository()
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                Log.d("xxx", "view model refresh")
                billboardRepository.refreshChart()
                _isNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (e: Exception) {
                _isNetworkError.value = true
            }
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(Hot100ViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return Hot100ViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}