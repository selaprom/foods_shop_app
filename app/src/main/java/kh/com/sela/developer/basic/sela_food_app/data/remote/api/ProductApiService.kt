package kh.com.sela.developer.basic.sela_food_app.data.remote.api

import kh.com.sela.developer.basic.sela_food_app.domain.model.request.DeleteFavoriteParams
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ApiResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductByCategoryResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ToggleFavoriteResponse
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): Response<ApiResponse>

    @GET("productsbycategory/{id}")
    suspend fun getProductsByCategory(@Path("id") categoryId: Int): Response<ApiResponse>

    @POST("favorites/toggle")
    suspend fun toggleFavorite(
        @Body params: DeleteFavoriteParams
    ): Response<ToggleFavoriteResponse>
}