package kh.com.sela.developer.basic.sela_food_app.domain.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val data: List<ProductDto>
)
@Serializable
data class ProductDto(
    val id: Int,
    @SerialName("category_id") val categoryId: Int? = null,
    val category: String,
    val name: String,
    val description: String? = null,
    val price: String,                      // "1.60" is a string in the JSON
    @SerialName("image_url") val imageUrl: String? = null,
    val stock: Int,
    @SerialName("is_available") val isAvailable: Boolean
)