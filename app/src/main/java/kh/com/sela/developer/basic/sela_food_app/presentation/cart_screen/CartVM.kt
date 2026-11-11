package kh.com.sela.developer.basic.sela_food_app.presentation.cart_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItemDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartResponse
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.cartusecase.GetCartUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartVM @Inject constructor(
    private val getCartUseCase: GetCartUseCase
) : ViewModel() {

    private val _cartState =
        MutableStateFlow<BaseUiState<CartResponse<List<CartItemDto>>>>(BaseUiState.None)

    val cartState = _cartState.asStateFlow()
    suspend fun getCartItems() {
        getCartUseCase.invoke(Unit).collect {
            _cartState.value = it

        }
    }

}