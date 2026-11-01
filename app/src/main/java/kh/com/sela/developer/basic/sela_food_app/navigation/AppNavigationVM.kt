package kh.com.sela.developer.basic.sela_food_app.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.sela.developer.basic.sela_food_app.Util.AppStartDestination
import kh.com.sela.developer.basic.sela_food_app.Util.DataStoreManager
import kh.com.sela.developer.basic.sela_food_app.data.local.TokenStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AppNavigationVM @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val tokenManager: TokenStore
): ViewModel(){



private val _startDestination =
    MutableStateFlow<AppStartDestination>(
        AppStartDestination.Loading
    )

val startDestination =
    _startDestination.asStateFlow()

init {
    checkAppStart()
}

private fun checkAppStart() {

    viewModelScope.launch {

        val isFirstTime =
            dataStoreManager.isFirstTime.first()

        if (isFirstTime) {

            _startDestination.value =
                AppStartDestination.Welcome

        } else {

            val token =
                tokenManager.token

            _startDestination.value =
                if (!token.isNullOrBlank()) {
                    AppStartDestination.Main
                } else {
                    AppStartDestination.Login
                }
        }
    }
}
    fun completeOnboarding() {

        viewModelScope.launch {

            dataStoreManager.saveFirstTime(false)

            val token = tokenManager.token

            _startDestination.value =
                if (!token.isNullOrBlank()) {
                    AppStartDestination.Main
                } else {
                    AppStartDestination.Login
                }
        }
    }
}