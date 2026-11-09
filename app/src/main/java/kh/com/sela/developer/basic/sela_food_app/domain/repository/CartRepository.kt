package kh.com.sela.developer.basic.sela_food_app.domain.repository

import kh.com.sela.developer.basic.sela_food_app.domain.model.request.AddToCartParams
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItemResponse
import retrofit2.Response

interface CartRepository{
    suspend fun addToCart(addToCartParams: AddToCartParams): Response<CartItemResponse>
}
