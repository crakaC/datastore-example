package com.crakac.datastoreexample.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.crakac.datastoreexample.UserProto
import com.crakac.datastoreexample.data.proto.UserSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATA_STORE_FILE_NAME = "user_prefs.pb"

@Module
@InstallIn(SingletonComponent::class)
object ProtoDataStoreModule {
    @Provides
    @Singleton
    fun provideProtoDataStore(@ApplicationContext context: Context): DataStore<UserProto> {
        return DataStoreFactory.create(
            serializer = UserSerializer,
            produceFile = { context.dataStoreFile(DATA_STORE_FILE_NAME) },
        )
    }
}