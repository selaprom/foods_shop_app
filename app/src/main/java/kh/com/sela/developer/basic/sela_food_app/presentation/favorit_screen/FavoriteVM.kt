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

    val favoriteUiState = _favoriteUiState.asStateFlow()

    val deleteFavoriteUiState = _deleteFavoriteUiState.asStateFlow()
    fun setIsShowDailog(isShow: Boolean){
        _isShowDailog.value = isShow
    }
    fun getFavorites(userId: Int){
        viewModelScope.launch {
            favoritesUseCase(userId).collect{
                _favoriteUiState.value = it
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