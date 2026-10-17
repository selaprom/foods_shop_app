package kh.com.sela.developer.basic.sela_food_app.data.base

sealed class BaseUiState<out T>{
    data class Success<T>(val data: T) : BaseUiState<T>()
    data class Error(val message: String) : BaseUiState<Nothing>()
    object Loading : BaseUiState<Nothing>()

    data object None: BaseUiState<Nothing>()

    data class ErrorException(val message: String,val throwable: Throwable?=null): BaseUiState<Nothing>()


}