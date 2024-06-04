package com.example.authapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.authapp.data.repository.DataStoreOperationsImpl
import com.example.authapp.data.repository.RepositoryImpl
import com.example.authapp.domain.repository.DataStoreOperations
import com.example.authapp.domain.repository.Repository
import com.example.authapp.util.Constants.PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStorePreferences(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(name = PREFERENCES_NAME)
            }
        )
    }

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        dataStore: DataStore<Preferences>,
    ): DataStoreOperations {
        return DataStoreOperationsImpl(
            dataStore = dataStore
        )
    }

    @Provides
    @Singleton
    fun providesRepository(
        dataStoreOperations: DataStoreOperations,
    ): Repository {
        return RepositoryImpl(
            dataStoreOperations = dataStoreOperations
        )
    }
}
