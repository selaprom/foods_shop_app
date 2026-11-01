package kh.com.sela.developer.basic.sela_food_app.Util

sealed interface AppStartDestination {
    data object Loading : AppStartDestination
    data object Welcome : AppStartDestination
    data object Login : AppStartDestination
    data object Main : AppStartDestination
}