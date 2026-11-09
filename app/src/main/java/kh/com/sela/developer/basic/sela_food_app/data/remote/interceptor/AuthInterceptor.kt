package kh.com.sela.developer.basic.sela_food_app.data.remote.interceptor

import android.util.Log
import kh.com.sela.developer.basic.sela_food_app.data.local.TokenStore
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenStore: TokenStore
) : Interceptor {

    override fun intercept(
        chain: Interceptor.Chain
    ): Response {

        val token = tokenStore.token

        Log.d(
            "Auth",
            "Authorization token exists: ${!token.isNullOrEmpty()}"
        )

        val request = chain.request()
            .newBuilder()
            .header("Accept", "application/json")
            .apply {
                if (!token.isNullOrBlank()) {
                    header("Authorization", "Bearer $token")
                }
            }
            .header("Accept", "application/json")
            .build()

        return chain.proceed(request)
    }
}