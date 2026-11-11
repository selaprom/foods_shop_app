package kh.com.sela.developer.basic.sela_food_app.presentation.product_detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.AddToCartParams
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItemResponse
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.cartusecase.AddToCartUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailVM @Inject constructor(private val addToCartUseCase: AddToCartUseCase) :
    ViewModel() {

    private val _addToCartState = MutableStateFlow<BaseUiState<CartItemResponse>>(BaseUiState.None)
    val addToCartState = _addToCartState.asStateFlow()
    private val _quantity = MutableStateFlow(1)
    val quantity = _quantity.asStateFlow()
    fun increaseQuantity() {
        _quantity.update { it + 1 }
    }

    fun decreaseQuantity() {
        _quantity.update { if (it > 1) it - 1 else 1 }
    }



    fun addToCart(productId: Int, quantity: Int) {
        viewModelScope.launch {
            val param = AddToCartParams(productId, quantity)
            addToCartUseCase(param).collect {
                _addToCartState.value = it
            }
        }
    }
    fun resetQuantity(){
        _quantity.value = 1
    }
    fun resetState(){
        _addToCartState.value = BaseUiState.None
    }


}