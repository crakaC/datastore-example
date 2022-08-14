package com.crakac.datastoreexample.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


private const val USER_PREFERENCES_NAME = "user_prefs"
private val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)

@Module
@InstallIn(SingletonComponent::class)
object PrefsDataStoreModule {
    @Provides
    @Singleton
    fun providePrefsDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}