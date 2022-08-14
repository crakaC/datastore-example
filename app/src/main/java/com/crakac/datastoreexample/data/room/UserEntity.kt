package com.crakac.datastoreexample.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "uuid", index = true)
    val uuid: String,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long
)