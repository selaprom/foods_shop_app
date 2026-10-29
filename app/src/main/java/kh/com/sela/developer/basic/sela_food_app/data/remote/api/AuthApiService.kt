package kh.com.sela.developer.basic.sela_food_app.data.remote.api

import kh.com.sela.developer.basic.sela_food_app.domain.model.request.LoginRequest
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.RegisterRequest
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.LoginResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.LogoutResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService{
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("logout") suspend fun logout(): Response<LogoutResponse>

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
}
