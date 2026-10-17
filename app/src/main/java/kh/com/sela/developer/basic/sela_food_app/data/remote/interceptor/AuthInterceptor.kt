package kh.com.sela.developer.basic.sela_food_app.data.remote.interceptor

import kh.com.sela.developer.basic.sela_food_app.data.local.TokenStore
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenStore: TokenStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .apply {
                tokenStore.token?.let { addHeader("Authorization", "Bearer $it") }
            }
            .build()
        return chain.proceed(request)
    }
}