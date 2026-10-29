package kh.com.sela.developer.basic.sela_food_app.domain.usecase.AuthUseCase

import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.LogoutResponse
import kh.com.sela.developer.basic.sela_food_app.domain.repository.AuthRepository
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val authRepository: AuthRepository) : BaseUseCase<Unit, Flow<BaseUiState<LogoutResponse>>>(){
    override suspend fun execute(param: Unit): Flow<BaseUiState<LogoutResponse>> {
        return flow {
            try {
                emit(BaseUiState.Loading)
                val response = authRepository.logout()
                if (response.isSuccessful) {
                    emit(BaseUiState.Success(response.body()!!))
                }
            } catch (e: Exception) {
                emit(BaseUiState.Error(e.message.toString()))
            }
        }
        }

}