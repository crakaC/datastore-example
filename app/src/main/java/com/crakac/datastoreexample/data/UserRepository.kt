package com.crakac.datastoreexample.data

import android.util.Log
import com.crakac.datastoreexample.data.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException
import java.util.*
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    companion object {
        private val TAG = Companion::class.java.simpleName
    }

    val users: Flow<List<User>> = userDao.getAll().catch { exception ->
        if (exception is IOException) {
            Log.e(TAG, "Error reading db.", exception)
            emit(emptyList())
        } else {
            throw exception
        }
    }

    suspend fun add() {
        userDao.insert(User(UUID.randomUUID().toString(), System.currentTimeMillis()))
    }

    suspend fun update(userId: String) {
        userDao.update(User(userId, System.currentTimeMillis()))
    }

    suspend fun clear() {
        userDao.deleteAll()
    }
}