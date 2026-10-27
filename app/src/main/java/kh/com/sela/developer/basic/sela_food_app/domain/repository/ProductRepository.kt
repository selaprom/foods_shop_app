package kh.com.sela.developer.basic.sela_food_app.domain.repository

import kh.com.sela.developer.basic.sela_food_app.domain.model.request.DeleteFavoriteParams
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ApiResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductByCategoryResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ToggleFavoriteResponse
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.favoriteusecase.DeleteFavoriteUseCase
import retrofit2.Response
interface ProductRepository {
    suspend fun getProducts(): Response<ApiResponse>
    suspend fun getProductByCategory(category: Int): Response<ApiResponse>

    suspend fun toggleFavorite(userIdProductId: DeleteFavoriteParams): Response<ToggleFavoriteResponse>

}