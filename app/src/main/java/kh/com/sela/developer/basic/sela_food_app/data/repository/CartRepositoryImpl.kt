package kh.com.sela.developer.basic.sela_food_app.data.repository

import kh.com.sela.developer.basic.sela_food_app.data.remote.api.CartService
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.AddToCartParams
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItemDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItemResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartResponse
import kh.com.sela.developer.basic.sela_food_app.domain.repository.CartRepository
import retrofit2.Response
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartService: CartService
): CartRepository {
    override suspend fun addToCart(
        addToCartParams: AddToCartParams
    ): Response<CartItemResponse> {
        val response = cartService.addToCart(addToCartParams)
        if (response.isSuccessful) {
            return response

        }
        throw Exception("Failed to add item to cart")
    }

    override suspend fun getCartItems(): Response<CartResponse<List<CartItemDto>>> {
        val response = cartService.getCartItems()
        if (response.isSuccessful) {
            return response
        }
        throw Exception("Failed to get cart items")
    }

}