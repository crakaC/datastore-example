package com.crakac.datastoreexample.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val STORE_FILE_NAME = "prefs_store"

@Module
@InstallIn(SingletonComponent::class)
object PrefsDataStoreModule {
    @Provides
    @Singleton
    fun providePrefsDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(STORE_FILE_NAME)
        }
    }
}