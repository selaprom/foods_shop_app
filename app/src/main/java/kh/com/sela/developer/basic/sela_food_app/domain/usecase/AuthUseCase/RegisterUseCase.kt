package kh.com.sela.developer.basic.sela_food_app.domain.usecase.AuthUseCase

import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.RegisterRequest
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.RegisterResponse
import kh.com.sela.developer.basic.sela_food_app.domain.repository.AuthRepository
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val authRepository: AuthRepository) : BaseUseCase<RegisterRequest, Flow<BaseUiState<RegisterResponse>>>() {
    override suspend fun execute(param: RegisterRequest): Flow<BaseUiState<RegisterResponse>> {
        return flow {
            try {
                emit(BaseUiState.Loading)
                val response = authRepository.register(param)
                if (response.isSuccessful) {
                    emit(BaseUiState.Success(response.body()!!))
                }

            }catch (e: Exception){
                emit(BaseUiState.Error(e.message.toString()))
            }
        }


    }
}