package kh.com.sela.developer.basic.sela_food_app.presentation.login_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.LoginRequest
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.LoginResponse
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.AuthUseCase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
    private val loginUseCase: LoginUseCase

) : ViewModel() {

    private val _loginUiState = MutableStateFlow<BaseUiState<LoginResponse>>(BaseUiState.None)
    val loginUiState = _loginUiState.asStateFlow()


    fun login(email: String, password: String) {
        viewModelScope.launch {
            val param = LoginRequest(email, password)
            loginUseCase(param).collect {
                _loginUiState.value = it
            }
        }
    }


}