package kh.com.sela.developer.basic.sela_food_app.Util


import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(
    name = "app_preferences"
)

@Singleton
class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private val FIRST_TIME_KEY =
            booleanPreferencesKey("first_time")
    }

    // Read first-time status
    val isFirstTime: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[FIRST_TIME_KEY] ?: true
        }

    // Save onboarding status
    suspend fun saveFirstTime(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[FIRST_TIME_KEY] = value
        }
    }
}