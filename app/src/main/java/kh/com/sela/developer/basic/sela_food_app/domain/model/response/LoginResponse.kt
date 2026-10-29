package kh.com.sela.developer.basic.sela_food_app.domain.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class LoginResponse(
    val user: AuthUser,
    val token: String,
)

@Serializable
data class AuthUser(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String? = null,
    @SerialName("image_url") val imageUrl: String? = null,
)