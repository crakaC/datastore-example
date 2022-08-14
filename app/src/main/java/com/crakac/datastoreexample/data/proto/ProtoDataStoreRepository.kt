package com.crakac.datastoreexample.data.proto

import android.util.Log
import androidx.datastore.core.DataStore
import com.crakac.datastoreexample.UserProto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException
import javax.inject.Inject

class ProtoDataStoreRepository @Inject constructor(
    private val dataStore: DataStore<UserProto>,
) {
    companion object {
        private val TAG = Companion::class.java.simpleName
    }

    val userPreferencesFlow: Flow<UserProto> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            Log.e(TAG, "Error reading sort order preferences.", exception)
            emit(UserProto.getDefaultInstance())
        } else {
            throw exception
        }
    }

    suspend fun update(userId: String) {
        dataStore.updateData {
            it.toBuilder().putData(userId, System.currentTimeMillis()).build()
        }
    }

    suspend fun clear() {
        dataStore.updateData {
            it.toBuilder().clearData().build()
        }
    }
}