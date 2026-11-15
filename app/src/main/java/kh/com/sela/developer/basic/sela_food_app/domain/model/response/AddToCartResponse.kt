package kh.com.sela.developer.basic.sela_food_app.domain.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


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