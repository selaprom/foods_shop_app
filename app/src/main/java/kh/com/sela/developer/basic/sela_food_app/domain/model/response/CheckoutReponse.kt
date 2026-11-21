package kh.com.sela.developer.basic.sela_food_app.domain.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckoutResponse(
    val success: Boolean,
    val message: String,
    val data: OrderDto
)

@Serializable
data class OrderDto(
    val id: Int,
    @SerialName("user_id") val userId: Int,
    @SerialName("address_id") val addressId: Int,
    @SerialName("order_number") val orderNumber: String,
    val status: String,
    val subtotal: String,
    @SerialName("delivery_fee") val deliveryFee: String,
    val total: String,
    val items: List<OrderItemDto> = emptyList(),
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null
)

@Serializable
data class OrderItemDto(
    val id: Int,
    @SerialName("order_id") val orderId: Int,
    @SerialName("product_id") val productId: Int,
    @SerialName("product_name") val productName: String,
    @SerialName("unit_price") val unitPrice: String,
    val quantity: Int,
    val subtotal: String
)