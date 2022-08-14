package com.crakac.datastoreexample.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*
import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    private val prefsDataStore: DataStore<Preferences>
) {
    companion object {
        private val KEY = stringPreferencesKey("users")
    }

    val users: Flow<List<Pair<String, Long>>> = prefsDataStore.data.map {
        val str = it[KEY] ?: return@map emptyList()
        Json.decodeFromString<UserPrefs>(str).dataMap.toList()
    }

    private suspend fun edit(block: (UserPrefs) -> Unit) {
        prefsDataStore.edit {
            val str = it[KEY]
            val userPrefs = UserPrefs.fromJsonString(str)
            block(userPrefs)
            it[KEY] = Json.encodeToString(userPrefs)
        }
    }

    suspend fun add() {
        edit { userPrefs ->
            userPrefs.dataMap[UUID.randomUUID().toString()] = System.currentTimeMillis()
        }
    }

    suspend fun update(id: String) {
        edit { userPrefs ->
            userPrefs.dataMap[id] = System.currentTimeMillis()
        }
    }

    suspend fun delete(id: String) {
        edit { userPrefs ->
            userPrefs.dataMap.remove(id)
        }
    }

    suspend fun clear() {
        prefsDataStore.edit { it.clear() }
    }
}