package kh.com.sela.developer.basic.sela_food_app.domain.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductByCategoryResponse(

    val data: List<ProductDto>
)
