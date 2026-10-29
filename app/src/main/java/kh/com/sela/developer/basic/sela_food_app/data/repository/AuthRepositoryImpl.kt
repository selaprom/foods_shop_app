package kh.com.sela.developer.basic.sela_food_app.data.repository

import kh.com.sela.developer.basic.sela_food_app.data.remote.api.AuthApiService
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.LoginRequest
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.RegisterRequest
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.LoginResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.LogoutResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.RegisterResponse
import kh.com.sela.developer.basic.sela_food_app.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authApiService: AuthApiService) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return authApiService.login(loginRequest)
    }

    override suspend fun logout(): Response<LogoutResponse> {
        return authApiService.logout()
    }

    override suspend fun register(registerRequest: RegisterRequest): Response<RegisterResponse> {
        return authApiService.register(registerRequest)
    }
}