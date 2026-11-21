package kh.com.sela.developer.basic.sela_food_app.domain.usecase.cartusecase

import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.CheckoutRequest
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CheckoutResponse
import kh.com.sela.developer.basic.sela_food_app.domain.repository.CartRepository
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckoutUseCase @Inject constructor(private val cartRepository: CartRepository): BaseUseCase<CheckoutRequest, CheckoutResponse>(){
    override suspend fun execute(param: CheckoutRequest): CheckoutResponse {

        val response = cartRepository.checkout(param)
        if (response.isSuccessful) {
            return response.body()!!
        }
        throw Exception("Failed to checkout")
    }

}