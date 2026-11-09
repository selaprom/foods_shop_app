package kh.com.sela.developer.basic.sela_food_app.domain.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AddToCartParams(
    @SerialName("product_id") val productId: Int,
    val quantity: Int
)