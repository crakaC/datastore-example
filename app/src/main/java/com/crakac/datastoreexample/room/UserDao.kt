package com.crakac.datastoreexample.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): Flow<List<UserEntity>>

    @Insert
    suspend fun insert(user: UserEntity)

    @Query("DELETE FROM users")
    suspend fun deleteAll()

    @Query("DELETE FROM users WHERE uuid = :uuid")
    suspend fun deleteByUUID(uuid: String)

    @Update(entity = UserEntity::class)
    suspend fun update(user: UserEntity)
}