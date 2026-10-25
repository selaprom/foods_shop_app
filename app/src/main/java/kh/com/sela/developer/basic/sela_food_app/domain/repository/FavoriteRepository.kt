package kh.com.sela.developer.basic.sela_food_app.domain.repository

import kh.com.sela.developer.basic.sela_food_app.domain.model.request.DeleteFavoriteParams
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.FavoriteResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ToggleFavoriteResponse
import retrofit2.Response

interface FavoriteRepository {
    suspend fun getFavorites(userId: Int): Response<FavoriteResponse>
    suspend fun createFavorite(userId: Int, productId: Int): Response<Unit>
    suspend fun toggleFavorite(userIdProducId: DeleteFavoriteParams): Response<ToggleFavoriteResponse>
}
