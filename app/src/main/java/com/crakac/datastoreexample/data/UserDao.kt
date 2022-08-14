package com.crakac.datastoreexample.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.crakac.datastoreexample.data.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): Flow<List<User>>

    @Insert
    suspend fun insert(user: User)

    @Query("DELETE FROM users")
    suspend fun deleteAll()

    @Query("DELETE FROM users WHERE uuid = :uuid")
    suspend fun deleteByUUID(uuid: String)

    @Update(entity = User::class)
    suspend fun update(user: User)
}