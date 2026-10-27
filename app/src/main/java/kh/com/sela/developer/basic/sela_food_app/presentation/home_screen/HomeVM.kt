package kh.com.sela.developer.basic.sela_food_app.presentation.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.DeleteFavoriteParams
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ApiResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductByCategoryResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ToggleFavoriteResponse
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.favoriteusecase.GetFavoritesUseCase
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.productusecase.GetProductByCategoryUseCase
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.productusecase.GetProductUseCase
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.productusecase.ToggleFavoriteProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeVM @Inject constructor(
    val getProductUseCase: GetProductUseCase,
    val getProductByCategoryUseCase: GetProductByCategoryUseCase,
    val toggleFavoriteProductUseCase: ToggleFavoriteProductUseCase,
    val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {
    private val _productUiState = MutableStateFlow<BaseUiState<ApiResponse>>(BaseUiState.None)
    private val _categoryType = MutableStateFlow<String>("All")
    private val _toggleFavoriteProduct =
        MutableStateFlow<BaseUiState<ToggleFavoriteResponse>>(BaseUiState.None)
    val toggleFavoriteProduct = _toggleFavoriteProduct.asStateFlow()
    val productUiState = _productUiState.asStateFlow()
    val categoryType = _categoryType.asStateFlow()

    private val _listOfselectProduct = MutableStateFlow<Set<Int>>(emptySet())

    val listOfselectProduct = _listOfselectProduct.asStateFlow()
    fun setListOfselectProduct(productId: Int) {
        _listOfselectProduct.update { current ->
            if (productId in current) current - productId else current + productId
        }

    }


    fun getProducts() {
        viewModelScope.launch {
            getProductUseCase(Unit).collect {

                _productUiState.value = it


            }


        }}

    fun toggleSelecteFavoriteProduct(userId: Int, productId: Int) {
        viewModelScope.launch {
            val params = DeleteFavoriteParams(userId, productId)
            toggleFavoriteProductUseCase(params).collect {
                _toggleFavoriteProduct.value = it
            }



        }
    }

    fun loadFavorites(userId: Int) {
        viewModelScope.launch {
            getFavoritesUseCase(userId).collect { state ->
                when (state) {
                    is BaseUiState.Success -> {
                        println("data=========>from viewmodel ${state.data.data}")
                        _listOfselectProduct.value = state.data.data
                            .map { it.productId }
                            .toSet()
                    }
                    is BaseUiState.Error -> Log.e("Favorites", state.message ?: "error")
                    is BaseUiState.Loading -> Unit
                    else -> {}
                }
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







