package kh.com.sela.developer.basic.sela_food_app.data.repository

import kh.com.sela.developer.basic.sela_food_app.data.remote.api.ProductApiService
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.DeleteFavoriteParams
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ApiResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductByCategoryResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ToggleFavoriteResponse
import kh.com.sela.developer.basic.sela_food_app.domain.repository.ProductRepository
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.favoriteusecase.DeleteFavoriteUseCase
import retrofit2.Response
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productApiService: ProductApiService) :
    ProductRepository {
    override suspend fun getProducts(): Response<ApiResponse>{
        return productApiService.getProducts()

    }

    override suspend fun getProductByCategory(category: Int): Response<ApiResponse> {
        return productApiService.getProductsByCategory(category)
    }

    override suspend fun toggleFavorite(
        userIdProductId: DeleteFavoriteParams
    ): Response<ToggleFavoriteResponse> {
        return productApiService.toggleFavorite(userIdProductId)
    }

}