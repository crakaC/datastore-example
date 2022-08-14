package com.crakac.datastoreexample.ui.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crakac.datastoreexample.preferences.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    val users: StateFlow<List<Pair<String, Long>>> = preferencesRepository.users.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun add() {
        viewModelScope.launch {
            preferencesRepository.add()
        }
    }

    fun update(id: String) {
        viewModelScope.launch {
            preferencesRepository.update(id)
        }
    }

    fun delete(id: String) {
        viewModelScope.launch {
            preferencesRepository.delete(id)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            preferencesRepository.clear()
        }
    }
}