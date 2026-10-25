package kh.com.sela.developer.basic.sela_food_app.domain.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ToggleFavoriteResponse(
    @SerialName("message") val message: String? = null,
    @SerialName("is_favorite") val isFavorite: Boolean = false
)