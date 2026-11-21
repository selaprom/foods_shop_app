package kh.com.sela.developer.basic.sela_food_app.domain.repository

import kh.com.sela.developer.basic.sela_food_app.domain.model.request.AddToCartParams
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.CheckoutRequest
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItemDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItemResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CheckoutResponse
import retrofit2.Response
import retrofit2.http.Body

interface CartRepository {
    suspend fun addToCart(addToCartParams: AddToCartParams): Response<CartItemResponse>
    suspend fun getCartItems(): Response<CartResponse>

    suspend fun checkout(checkoutRequest: CheckoutRequest): Response<CheckoutResponse>


}
