package com.crakac.datastoreexample.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "uuid", index = true)
    val uuid: String,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long
)