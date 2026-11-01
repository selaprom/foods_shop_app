package kh.com.sela.developer.basic.sela_food_app.navigation

import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductDto
import kotlinx.serialization.Serializable

data object MainScreenRoute
data object ScreenCartRoute

data object AppNavigation
data object LoginRoute

data object SignupRoute

data object WelcomeRoute


data object SplashRoute
@Serializable
data class ProductDetails( val product: ProductDto)