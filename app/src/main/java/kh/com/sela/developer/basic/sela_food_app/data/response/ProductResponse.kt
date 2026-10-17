package kh.com.sela.developer.basic.sela_food_app.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ProductDto(
    @SerialName("id") val id: Int,
    @SerialName("category_id") val categoryId: Int,
    @SerialName("category") val category: String,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("price") val price: String,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("stock") val stock: Int,
    @SerialName("is_available") val isAvailable: Boolean
)
