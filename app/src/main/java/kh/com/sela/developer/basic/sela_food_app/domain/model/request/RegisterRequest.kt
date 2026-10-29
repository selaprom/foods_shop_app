package kh.com.sela.developer.basic.sela_food_app.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val phone: String?=null,
)