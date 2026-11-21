package kh.com.sela.developer.basic.sela_food_app.presentation.cart_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.CheckoutItemRequest
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.CheckoutRequest
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItem
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItemDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.toDomain
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.cartusecase.CheckoutUseCase
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.cartusecase.GetCartUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.receiveAsFlow
import kotlin.coroutines.cancellation.CancellationException



data class CartUiState(
    val items: List<CartItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isCheckingOut: Boolean = false,
    val deliveryFee: Double = 1.5

) {
    val subtotal: Double get() = items.sumOf { it.total }
    val grandTotal: Double get() = if (items.isEmpty()) 0.0 else subtotal + deliveryFee
}

sealed interface CartEvent {
    data object CheckoutSuccess : CartEvent
    data class ShowMessage(val msg: String) : CartEvent
}

@HiltViewModel
class CartViewModel @Inject constructor(private val getCartUseCase: GetCartUseCase,
private val checkoutUseCase: CheckoutUseCase,
) : ViewModel() {


    private val _uiState = MutableStateFlow(CartUiState())
    val uiState = _uiState.asStateFlow()


    private val _events = Channel<CartEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()


//    init {
//        load()
//    }

    fun load() {
        viewModelScope.launch {
            getCartUseCase(Unit).collect { result ->
                Log.d("CART", "emit: $result")
                when (result) {
                    is BaseUiState.Loading ->
                        _uiState.update { it.copy(isLoading = true, error = null) }

                    is BaseUiState.Success -> {
                        val items = result.data.data.map { dto -> dto.toDomain() }
                        Log.d("CART", "success, items = ${items.size}")
                        _uiState.update { it.copy(items = items, isLoading = false, error = null) }
                    }

                    is BaseUiState.Error -> {
                        Log.e("CART", "error: ${result.message}")
                        _uiState.update { it.copy(isLoading = false, error = result.message) }
                    }

                    else -> Log.d("CART", "other state: $result")
                }
            }
        }}

    fun increase(id: Int) = updateQty(id, +1)
    fun decrease(id: Int) = updateQty(id, -1)

    private fun updateQty(id: Int, delta: Int) = _uiState.update { s ->
        s.copy(items = s.items.map {
            if (it.id == id) it.copy(quantity = (it.quantity + delta).coerceAtLeast(1)) else it
        })
    }
    fun checkout() {
        val s = _uiState.value
        if (s.items.isEmpty() || s.isCheckingOut) return
        viewModelScope.launch {
            _uiState.update { it.copy(isCheckingOut = true) }
            runCatching {
                checkoutUseCase.execute(
                    CheckoutRequest(
                        items = s.items.map { CheckoutItemRequest(it.productId, it.quantity) },
                        deliveryFee = s.deliveryFee
                    )
                )
            }.onSuccess {
                _uiState.update { it.copy(isCheckingOut = false, items = emptyList()) }
                _events.send(CartEvent.CheckoutSuccess)
            }.onFailure { e ->
                _uiState.update { it.copy(isCheckingOut = false) }
                _events.send(CartEvent.ShowMessage(e.message ?: "Checkout failed"))
                println("==========> ${e.message}")
            }
        }
    }

    fun remove(id: Int) = _uiState.update { s -> s.copy(items = s.items.filterNot { it.id == id }) }


}