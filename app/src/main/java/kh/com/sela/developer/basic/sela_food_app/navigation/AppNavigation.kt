package kh.com.sela.developer.basic.sela_food_app.navigation

import android.util.Log
import android.view.FrameMetrics.ANIMATION_DURATION
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import kh.com.sela.developer.basic.sela_food_app.Util.AppStartDestination
import kh.com.sela.developer.basic.sela_food_app.presentation.SignupScreen.SignupScreen
import kh.com.sela.developer.basic.sela_food_app.presentation.appnavigation_screen.AppNavigationScreen
import kh.com.sela.developer.basic.sela_food_app.presentation.cart_screen.ScreenCart
import kh.com.sela.developer.basic.sela_food_app.presentation.login_screen.LoginScreen
import kh.com.sela.developer.basic.sela_food_app.presentation.main_screen.MainScreen
import kh.com.sela.developer.basic.sela_food_app.presentation.product_detail_screen.ScreenProductDetails
import kh.com.sela.developer.basic.sela_food_app.presentation.splash_screen.SplashScreen
import kh.com.sela.developer.basic.sela_food_app.presentation.welcome_screen.WelcomeScreen

@Composable
fun AppNavigation(
    viewModel: AppNavigationVM= viewModel(),
) {

    val backStack = remember{ mutableStateListOf<Any>(SplashRoute) }
    val startDestination by
    viewModel.startDestination.collectAsStateWithLifecycle()


    LaunchedEffect(startDestination) {
        Log.d("NAV_DEBUG", "Start destination changed: $startDestination")

        val destination = when (startDestination) {
            AppStartDestination.Loading -> null
            AppStartDestination.Welcome -> WelcomeRoute
            AppStartDestination.Login -> LoginRoute
            AppStartDestination.Main -> MainScreenRoute
        }

        if (destination != null) {
            Log.d("NAV_DEBUG", "Applying destination: $destination")

            backStack.clear()
            backStack.add(destination)

            Log.d("NAV_DEBUG", "BackStack after startup: $backStack")
        }
    }


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
            entry<SplashRoute> {
                SplashScreen(
                    onFinished = {
                        Log.d("NAV_DEBUG", "Splash finished")
                    }
                )
            }

            entry<WelcomeRoute> {
                WelcomeScreen(
                    onLoginClick = {
                        viewModel.completeOnboarding()
                        backStack.add(LoginRoute)
                    },

                    onSignupClick = {
                        viewModel.completeOnboarding()
                        backStack.add(SignupRoute)
                    }
                )
            }
            entry<LoginRoute> {
                LoginScreen(
                    onClicktoSignup = {
                        backStack.add(SignupRoute)
                    },
                    onForward = { userId ->
                        Log.d("NAV_DEBUG", "Login success: $userId")

                        backStack.add(MainScreenRoute)

                        Log.d("NAV_DEBUG", "BackStack after login: $backStack")
                    }
                )
            }
            entry<SignupRoute> {
                SignupScreen(
                    onClicktoLogin = {
                        backStack.add(LoginRoute)
                    }
                )
            }

            entry<MainScreenRoute> {
                MainScreen(
                    onClicktoCart = {
                        backStack.add(ScreenCartRoute)
                    },
                    onSearchClick = {
                        //
                    },
                    onProductClick = {product->
                        backStack.add(ProductDetails(product))
                    },
                    onLogout = {
                        Log.d("NAVIGATION", "Logout navigation triggered")

                        backStack.clear()
                        backStack.add(LoginRoute)

                        Log.d("NAVIGATION", "BackStack: $backStack")
                    }
                )
            }
            entry<ScreenCartRoute> {
                ScreenCart(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }
            entry<ProductDetails> {
                ScreenProductDetails(
                    onBack = {
                        backStack.removeLastOrNull()
                    },
                    product = it.product,
                )
            }
            entry<AppNavigation> {
                AppNavigationScreen()
            }


        }
    )
}