package kh.com.sela.developer.basic.sela_food_app.domain.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class CartResponse(
    val success: Boolean,
    val data: List<CartItemDto>
)

@Serializable
data class CartItemDto(
    val id: Int,
    @SerialName("product_id") val productId: Int,
    val name: String,
    val description: String? = null,
    val category: CategoryDto? = null,
    @SerialName("image_url") val imageUrl: String? = null,
    @SerialName("unit_price") val unitPrice: Double,
    val quantity: Int,
    val total: Double
)

@Serializable
data class CategoryDto(
    val id: Int,
    val name: String
)