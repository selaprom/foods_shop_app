package kh.com.sela.developer.basic.sela_food_app.domain.usecase.cartusecase

import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.AddToCartParams
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItemResponse
import kh.com.sela.developer.basic.sela_food_app.domain.repository.CartRepository
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(private val cartRepository: CartRepository):BaseUseCase<AddToCartParams, Flow<BaseUiState<CartItemResponse>>>() {
    override suspend fun execute(param: AddToCartParams): Flow<BaseUiState<CartItemResponse>> {
        return flow {
            try {
                emit(BaseUiState.Loading)
                val response = cartRepository.addToCart(param)
                if (response.isSuccessful){
                    emit(BaseUiState.Success(response.body()!!))
                }else{
                    emit(BaseUiState.Error(response.message()))
                }}catch (e: Exception){
                    emit(BaseUiState.Error(e.message.toString()))
                }
            }
        }
    }
