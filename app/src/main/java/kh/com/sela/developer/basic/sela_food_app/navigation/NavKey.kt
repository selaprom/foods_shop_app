package kh.com.sela.developer.basic.sela_food_app.navigation

import kh.com.sela.developer.basic.sela_food_app.data.response.ProductDto
import kotlinx.serialization.Serializable

data object MainScreenRoute
data object ScreenCartRoute
@Serializable
data class ProductDetails( val product: ProductDto)