package kh.com.sela.developer.basic.sela_food_app.data.remote.api

import kh.com.sela.developer.basic.sela_food_app.domain.model.request.DeleteFavoriteParams
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.FavoriteResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ToggleFavoriteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FavoriteApiService {


    @GET("favorites")
    suspend fun getFavorites(
        @Query("user_id") userId: Int
    ): Response<FavoriteResponse>

    @POST("favorites")
    suspend fun createFavorite(
        @Query("user_id") userId: Int,
        @Query("product_id") productId: Int
    ): Response<Unit>

    @POST("favorites/toggle")
    suspend fun toggleFavorite(
        @Body params: DeleteFavoriteParams
    ): Response<ToggleFavoriteResponse>


}

