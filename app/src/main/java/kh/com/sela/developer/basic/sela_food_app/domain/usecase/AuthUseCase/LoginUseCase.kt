package kh.com.sela.developer.basic.sela_food_app.domain.usecase.AuthUseCase

import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.LoginRequest
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.LoginResponse
import kh.com.sela.developer.basic.sela_food_app.domain.repository.AuthRepository
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository): BaseUseCase<LoginRequest, Flow<BaseUiState<LoginResponse>>>() {
    override suspend fun execute(param: LoginRequest): Flow<BaseUiState<LoginResponse>> {
        return flow {
            try {
                emit(BaseUiState.Loading)
                val response = authRepository.login(param)
                if (response.isSuccessful){
                    emit(BaseUiState.Success(response.body()!!))
                }

            }catch (e: Exception){
                emit(BaseUiState.Error(e.message.toString()))
            }
        }

    }
}