package com.streafy.pizzashift2024.shared.tokenstorage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TokenStorageModule {

    companion object {
        private const val TOKEN_PREFERENCES_NAME = "token_preferences"

        @Singleton
        @Provides
        fun providePreferencesDataStore(
            @ApplicationContext context: Context
        ): DataStore<Preferences> =
            PreferenceDataStoreFactory.create(
                corruptionHandler = ReplaceFileCorruptionHandler(
                    produceNewData = { emptyPreferences() }
                ),
                scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
                produceFile = { context.preferencesDataStoreFile(TOKEN_PREFERENCES_NAME) }
            )
    }

    @Binds
    abstract fun bindTokenStorage(
        preferencesDataStoreTokenStorage: PreferencesDataStoreTokenStorage
    ): TokenStorage
}