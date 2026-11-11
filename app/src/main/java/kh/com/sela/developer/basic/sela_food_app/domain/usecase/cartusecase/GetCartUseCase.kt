package kh.com.sela.developer.basic.sela_food_app.domain.usecase.cartusecase

import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItemDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartResponse
import kh.com.sela.developer.basic.sela_food_app.domain.repository.CartRepository
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) : BaseUseCase<Unit, Flow<BaseUiState<CartResponse<List<CartItemDto>>>>>() {
    override suspend fun execute(param: Unit): Flow<BaseUiState<CartResponse<List<CartItemDto>>>> {
        return flow {
            try {
                emit(BaseUiState.Loading)
                val response = cartRepository.getCartItems()
                if (response.isSuccessful) {
                    emit(BaseUiState.Success(response.body()!!))
                } else {
                    emit(BaseUiState.Error(response.message()))
                }


            } catch (e: Exception) {
                emit(BaseUiState.Error(e.message.toString()))


            }
        }
    }

}