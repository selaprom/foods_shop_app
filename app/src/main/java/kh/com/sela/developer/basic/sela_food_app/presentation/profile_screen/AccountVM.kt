package kh.com.sela.developer.basic.sela_food_app.presentation.profile_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.data.local.TokenStore
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.LogoutResponse
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.AuthUseCase.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AccountVM @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val tokenManager: TokenStore
    ): ViewModel() {
        private val _logoutUiState = MutableStateFlow<BaseUiState<LogoutResponse>>(BaseUiState.None)
       val logoutUiState = _logoutUiState.asStateFlow()

    fun resetState() {
        _logoutUiState.value = BaseUiState.None
    }
        fun logout() {

            viewModelScope.launch {
                logoutUseCase.execute(Unit).collect {state ->
                    _logoutUiState.value = state
                    if (state is BaseUiState.Success){
                        tokenManager.clear()
                        Log.d("LOGOUT", "Token after clear: ${tokenManager.token}")
                    }

                }
            }

        }

    }