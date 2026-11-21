package kh.com.sela.developer.basic.sela_food_app.domain.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class CheckoutItemRequest(@SerialName("product_id") val productId: Int, val quantity: Int)

@Serializable
data class CheckoutRequest(val items: List<CheckoutItemRequest>, @SerialName("delivery_fee") val deliveryFee: Double)