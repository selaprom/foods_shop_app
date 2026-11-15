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
    val description: String,
    val category: CategoryDto,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("unit_price") val unitPrice: Double,
    val quantity: Int,
    val total: Double
)
data class CartItem(
    val id: Int,
    val productId: Int,
    val name: String,
    val category: String,
    val description: String,
    val imageUrl: String,
    val unitPrice: Double,
    val quantity: Int
) {
    val total: Double get() = unitPrice * quantity
}

fun CartItemDto.toDomain() = CartItem(id, productId, name, category.name,description, imageUrl, unitPrice, quantity,)
@Serializable
data class CategoryDto(
    val id: Int,
    val name: String
)