package kh.com.sela.developer.basic.sela_food_app.presentation.favorit_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.DeleteFavoriteParams
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.FavoriteResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ToggleFavoriteResponse
import kh.com.sela.developer.basic.sela_food_app.domain.repository.FavoriteRepository
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.favoriteusecase.DeleteFavoriteUseCase
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.favoriteusecase.GetFavoritesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteVM @Inject constructor(private val favoritesUseCase: GetFavoritesUseCase,
                                     private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    ): ViewModel(){
    private val _favoriteUiState = MutableStateFlow<BaseUiState<FavoriteResponse>>(BaseUiState.None)
    private  val _deleteFavoriteUiState = MutableStateFlow<BaseUiState<ToggleFavoriteResponse>>(BaseUiState.None)
    private val _isShowDailog = MutableStateFlow<Boolean>(false)
    val isShowDailog = _isShowDailog.asStateFlow()
    private val _listOfselectProduct = MutableStateFlow<Set<Int>>(emptySet())
    val listOfselectProduct = _listOfselectProduct.asStateFlow()

    val favoriteUiState = _favoriteUiState.asStateFlow()

    val deleteFavoriteUiState = _deleteFavoriteUiState.asStateFlow()

    fun setListFavoriteProductId(productId : Int){
        _listOfselectProduct.update { current ->
            if (productId in current) current - productId else current + productId

        }
    }

    fun getFavorites(userId: Int){
        viewModelScope.launch {
            favoritesUseCase(userId).collect{state ->
                _favoriteUiState.value = state
                when(state){
                    is BaseUiState.Success -> {
                        _listOfselectProduct.value = state.data.data
                            .map { it.productId }
                            .toSet()
                    }
                    else -> {
                        _listOfselectProduct.value = emptySet()
                    }
                }
            }
            _isShowDailog.value = false
        }


    }

    fun toggleFavorite(userIdProductId: DeleteFavoriteParams){

        viewModelScope.launch {
            deleteFavoriteUseCase(userIdProductId).collect{
                _deleteFavoriteUiState.value = it
            }

        }

    }



    fun onDispose() {
        _favoriteUiState.value = BaseUiState.None
    }
}