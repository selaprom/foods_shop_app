package kh.com.sela.developer.basic.sela_food_app.data.repository

import kh.com.sela.developer.basic.sela_food_app.data.remote.api.FavoriteApiService
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.DeleteFavoriteParams
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.FavoriteResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ToggleFavoriteResponse
import kh.com.sela.developer.basic.sela_food_app.domain.repository.FavoriteRepository
import retrofit2.Response
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(private val favoriteApiService: FavoriteApiService) :
    FavoriteRepository {
    override suspend fun getFavorites(userId: Int): Response<FavoriteResponse> {
        val result = favoriteApiService.getFavorites(userId)
        return result
        println("data=========>from repository $result")
    }

    override suspend fun createFavorite(
        userId: Int,
        productId: Int
    ): Response<Unit> {
        return favoriteApiService.createFavorite(userId, productId)
    }

    override suspend fun toggleFavorite(
        userIdProductId: DeleteFavoriteParams
    ): Response<ToggleFavoriteResponse> {
        return favoriteApiService.toggleFavorite(userIdProductId)
    }
}

