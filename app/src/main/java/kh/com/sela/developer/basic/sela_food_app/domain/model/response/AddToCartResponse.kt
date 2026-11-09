package kh.com.sela.developer.basic.sela_food_app.domain.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class CartItemDto(
    val id: Int,
    @SerialName("product_id") val productId: Int,
    val name: String,
    @SerialName("image_url") val imageUrl: String? = null,
    @SerialName("unit_price") val unitPrice: Double,
    val quantity: Int,
    val total: Double
)

@Serializable
data class CartItemResponse(
    val success: Boolean,
    val message: String? = null,
    val data: CartItemDto
)

@Serializable
data class CartListResponse(
    val success: Boolean,
    val data: List<CartItemDto>
)