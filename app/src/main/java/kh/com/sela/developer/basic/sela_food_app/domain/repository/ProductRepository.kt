package kh.com.sela.developer.basic.sela_food_app.domain.repository

import kh.com.sela.developer.basic.sela_food_app.data.response.ApiResponse
import kh.com.sela.developer.basic.sela_food_app.data.response.ProductDto
import retrofit2.Response
interface ProductRepository {
    suspend fun getProducts(): Response<ApiResponse<List<ProductDto>>>
}