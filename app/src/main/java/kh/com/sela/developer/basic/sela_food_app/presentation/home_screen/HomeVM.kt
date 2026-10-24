package kh.com.sela.developer.basic.sela_food_app.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ApiResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductByCategoryResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.productusecase.GetProductByCategoryUseCase
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.productusecase.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeVM @Inject constructor(
    val getProductUseCase: GetProductUseCase,
    val getProductByCategoryUseCase: GetProductByCategoryUseCase
) : ViewModel() {
    private val _productUiState = MutableStateFlow<BaseUiState<ApiResponse>>(BaseUiState.Loading)
    private val _categoryType = MutableStateFlow<String>("All")
    val productUiState = _productUiState.asStateFlow()
    val categoryType = _categoryType.asStateFlow()


    fun getProducts() {
        viewModelScope.launch {
            getProductUseCase(Unit).collect {
                _productUiState.value = it


                }

            }
        }

    fun setcategorytype(category: String) {
        _categoryType.value = category
    }
    fun onDispose() {
        _productUiState.value = BaseUiState.None
    }
    }






