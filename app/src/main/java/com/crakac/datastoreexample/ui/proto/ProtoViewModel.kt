package com.crakac.datastoreexample.ui.proto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crakac.datastoreexample.proto.ProtoDataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ProtoViewModel @Inject constructor(
    private val protoDataStoreRepository: ProtoDataStoreRepository
) : ViewModel() {
    val userPreferences: StateFlow<List<Pair<String, Long>>> =
        protoDataStoreRepository.userPreferencesFlow.map {
            it.dataMap.toList()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun addItem() {
        val id = UUID.randomUUID().toString()
        viewModelScope.launch {
            protoDataStoreRepository.update(id)
        }
    }

    fun clearItems() {
        viewModelScope.launch {
            protoDataStoreRepository.clear()
        }
    }

    fun updateItem(id: String) {
        viewModelScope.launch {
            protoDataStoreRepository.update(id)
        }
    }
}