package kh.com.sela.developer.basic.sela_food_app.navigation

import android.view.FrameMetrics.ANIMATION_DURATION
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import kh.com.sela.developer.basic.sela_food_app.navigation.MainScreenRoute
import kh.com.sela.developer.basic.sela_food_app.screens.cart_screen.ScreenCart
import kh.com.sela.developer.basic.sela_food_app.screens.main_screen.MainScreen

@Composable
fun AppNavigation() {
    val backStack = remember{ mutableStateListOf<Any>(MainScreenRoute) }

    NavDisplay(
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()

        },
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(ANIMATION_DURATION)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth / 3 },
                animationSpec = tween(ANIMATION_DURATION)
            )
        },
        popTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth / 3 },
                animationSpec = tween(ANIMATION_DURATION)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(ANIMATION_DURATION)
            )
        },
        entryProvider = entryProvider {

            entry<MainScreenRoute> {
                MainScreen(
                    onClicktoCart = {
                        backStack.add(ScreenCartRoute)
                    }
                ){
                    //
                }
            }
            entry<ScreenCartRoute> {
                ScreenCart(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }


        }
    )
}