package com.crakac.datastoreexample.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crakac.datastoreexample.UserPreferences
import com.crakac.datastoreexample.data.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    var userPreferences: UserPreferences by mutableStateOf(UserPreferences.getDefaultInstance())

    private val timer: Flow<Unit> = flow{
        while(true){
            delay(1_000)
            emit(Unit)
        }
    }
    init {
        viewModelScope.launch {
            timer.flatMapMerge { userPreferencesRepository.userPreferencesFlow }.collect{
                userPreferences = it
            }
        }
    }

    fun addItem() {
        val id = UUID.randomUUID().toString()
        viewModelScope.launch {
            userPreferencesRepository.update(id)
        }
    }

    fun clearItems() {
        viewModelScope.launch {
            userPreferencesRepository.clear()
        }
    }

    fun updateItem(id: String) {
        viewModelScope.launch {
            userPreferencesRepository.update(id)
        }
    }
}