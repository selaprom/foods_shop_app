package kh.com.sela.developer.basic.sela_food_app.data.remote.api

import kh.com.sela.developer.basic.sela_food_app.data.response.ApiResponse
import kh.com.sela.developer.basic.sela_food_app.data.response.ProductDto
import retrofit2.http.GET
import retrofit2.Response

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): Response<ApiResponse<List<ProductDto>>>
}