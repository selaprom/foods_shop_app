package kh.com.sela.developer.basic.sela_food_app.domain.repository

import kh.com.sela.developer.basic.sela_food_app.domain.model.request.LoginRequest
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.RegisterRequest
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.LoginResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.LogoutResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.RegisterResponse
import retrofit2.Response

interface AuthRepository{
    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse>
    suspend fun logout(): Response<LogoutResponse>
    suspend fun register(registerRequest: RegisterRequest): Response<RegisterResponse>

}