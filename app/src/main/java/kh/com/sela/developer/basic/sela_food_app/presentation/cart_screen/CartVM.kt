package kh.com.sela.developer.basic.sela_food_app.presentation.cart_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItem
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItemDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.toDomain
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.cartusecase.GetCartUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

//
//@HiltViewModel
//class CartVM @Inject constructor(
//    private val getCartUseCase: GetCartUseCase
//) : ViewModel() {
//
//    private val _cartState =
//        MutableStateFlow<BaseUiState<CartResponse>>(BaseUiState.None)
//
//    val cartState = _cartState.asStateFlow()
//    suspend fun getCartItems() {
//        getCartUseCase.invoke(Unit).collect {
//
//            _cartState.value = it
//
//        }
//    }
//
//}

data class CartUiState(
    val items: List<CartItem> = emptyList(),
    val deliveryFee: Double = 1.5,

) {
    val subtotal: Double get() = items.sumOf { it.total }
    val grandTotal: Double get() = if (items.isEmpty()) 0.0 else subtotal + deliveryFee
}

sealed interface CartEvent {
    data object CheckoutSuccess : CartEvent
    data class ShowMessage(val msg: String) : CartEvent
}

@HiltViewModel
class CartViewModel @Inject constructor(private val getCartUseCase: GetCartUseCase) : ViewModel() {

    private val _state = MutableStateFlow<BaseUiState<CartResponse>>(BaseUiState.None)
    val state = _state.asStateFlow()

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState = _uiState.asStateFlow()


    private val _events = Channel<CartEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun setItems(items: List<CartItem>) = _uiState.update { it.copy(items = items) }

    init { load() }

    fun load() {
        viewModelScope.launch {
            getCartUseCase(Unit).collect { result ->
                _state.value = result
            }
        }
    }

    fun increase(id: Int) = updateQty(id, +1)
    fun decrease(id: Int) = updateQty(id, -1)

    private fun updateQty(id: Int, delta: Int) = _uiState.update { s ->
        s.copy(items = s.items.map {
            if (it.id == id) it.copy(quantity = (it.quantity + delta).coerceAtLeast(1)) else it
        })
    }

    fun remove(id: Int) = _uiState.update { s -> s.copy(items = s.items.filterNot { it.id == id }) }


}