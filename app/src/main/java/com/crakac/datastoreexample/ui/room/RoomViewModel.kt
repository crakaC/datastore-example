package com.crakac.datastoreexample.ui.room

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crakac.datastoreexample.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    val users: StateFlow<List<Pair<String, Long>>> = userRepository.users.map {
        it.map { (id, updatedAt) -> id to updatedAt }
    }.onEach {
        Log.d(RoomViewModel::class.simpleName, "changed")
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun add() {
        viewModelScope.launch {
            userRepository.add()
        }
    }

    fun delete() {
        viewModelScope.launch {
            userRepository.clear()
        }
    }

    fun update(id: String) {
        viewModelScope.launch {
            userRepository.update(id)
        }
    }
}