package com.example.mybillboard.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybillboard.domain.SearchItem
import com.example.mybillboard.network.BillboardApi
import com.example.mybillboard.network.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel : ViewModel() {
    private val _items = MutableLiveData<List<SearchItem>?>()
    val item: LiveData<List<SearchItem>?>
        get() = _items

    fun search(term: String) {
        viewModelScope.launch {
            try {
                _items.value = BillboardApi.retrofitService.search(term).asDomainModel()
            } catch (e: Exception) {
                Log.d("xxx", "loiii")
                e.printStackTrace()
            }

        }
    }
}