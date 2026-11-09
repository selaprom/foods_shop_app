package kh.com.sela.developer.basic.sela_food_app.data.local

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenStore @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)

    var token: String?
        get() = prefs.getString("token", null)
        set(value) {
            Log.d("Auth", "SET token=${value?.take(10)}")
            prefs.edit().putString("token", value).apply()
        }

    fun clear() {
        Log.d("Auth", "CLEAR", Throwable("clear called"))
        prefs.edit().remove("token").apply()
    }
}