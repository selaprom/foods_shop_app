package kh.com.sela.developer.basic.sela_food_app.data.remote.api

import kh.com.sela.developer.basic.sela_food_app.domain.model.request.AddToCartParams
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ApiResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItemDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItemResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CartService{
    @POST("cart")
    suspend fun addToCart(@Body request: AddToCartParams): Response<CartItemResponse>

    @GET("cart")
    suspend fun getCartItems(): Response<CartResponse<List<CartItemDto>>>

}