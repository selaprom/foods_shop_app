package kh.com.sela.developer.basic.sela_food_app.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.data.response.ApiResponse
import kh.com.sela.developer.basic.sela_food_app.data.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.productusecase.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeVM @Inject constructor (val getProductUseCase: GetProductUseCase) : ViewModel(){
    private val _productUiState = MutableStateFlow<BaseUiState<ApiResponse<List<ProductDto>>>>(BaseUiState.Loading)
val productUiState = _productUiState.asStateFlow()

    fun getProducts() {
        viewModelScope.launch {
            getProductUseCase(Unit).collect {
                _productUiState.value = it
                println("ProductUiState:=====> $it")
            }
        }
    }


    fun onDispose() {
        _productUiState.value = BaseUiState.None
    }

}