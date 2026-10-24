package kh.com.sela.developer.basic.sela_food_app.domain.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteResponse(
    val data: List<FavoriteDto>
)

@Serializable
data class FavoriteDto(
    val id: Int,

    @SerialName("user_id")
    val userId: Int,

    @SerialName("product_id")
    val productId: Int,

    val product: ProductDto?
)