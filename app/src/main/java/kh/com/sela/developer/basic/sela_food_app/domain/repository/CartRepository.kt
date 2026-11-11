package kh.com.sela.developer.basic.sela_food_app.domain.repository

import kh.com.sela.developer.basic.sela_food_app.domain.model.request.AddToCartParams
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItemDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItemResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartResponse
import retrofit2.Response

interface CartRepository {
    suspend fun addToCart(addToCartParams: AddToCartParams): Response<CartItemResponse>
    suspend fun getCartItems(): Response<CartResponse<List<CartItemDto>>>

}
