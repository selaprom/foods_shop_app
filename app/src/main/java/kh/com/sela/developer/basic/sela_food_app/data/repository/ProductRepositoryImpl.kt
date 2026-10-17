package kh.com.sela.developer.basic.sela_food_app.data.repository

import kh.com.sela.developer.basic.sela_food_app.data.remote.api.ProductApiService
import kh.com.sela.developer.basic.sela_food_app.data.response.ApiResponse
import kh.com.sela.developer.basic.sela_food_app.data.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.domain.repository.ProductRepository
import retrofit2.Response
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productApiService: ProductApiService) :
    ProductRepository {
    override suspend fun getProducts(): Response<ApiResponse<List<ProductDto>>>{
        return productApiService.getProducts()

    }
}