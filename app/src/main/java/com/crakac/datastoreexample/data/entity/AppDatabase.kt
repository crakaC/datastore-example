package com.crakac.datastoreexample.data.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.crakac.datastoreexample.data.UserDao

@Database(entities = [User::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}