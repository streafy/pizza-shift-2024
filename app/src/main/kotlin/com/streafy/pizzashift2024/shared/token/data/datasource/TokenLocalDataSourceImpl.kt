package com.streafy.pizzashift2024.shared.token.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : TokenLocalDataSource {

    private companion object {
        val TOKEN_KEY = stringPreferencesKey("token")
    }

    override suspend fun save(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    override suspend fun get(): String? =
        dataStore.data.first()[TOKEN_KEY]

    override suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
}