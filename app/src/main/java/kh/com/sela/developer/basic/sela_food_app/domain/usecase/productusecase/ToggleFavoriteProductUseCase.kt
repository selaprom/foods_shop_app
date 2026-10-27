package kh.com.sela.developer.basic.sela_food_app.domain.usecase.productusecase

import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.DeleteFavoriteParams
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.FavoriteResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ToggleFavoriteResponse
import kh.com.sela.developer.basic.sela_food_app.domain.repository.FavoriteRepository
import kh.com.sela.developer.basic.sela_food_app.domain.repository.ProductRepository
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.BaseUseCase
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.favoriteusecase.DeleteFavoriteUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ToggleFavoriteProductUseCase @Inject constructor(private val productRepository: ProductRepository) :
    BaseUseCase<DeleteFavoriteParams, Flow<BaseUiState<ToggleFavoriteResponse>>>() {
    override suspend fun execute(param: DeleteFavoriteParams):  Flow<BaseUiState<ToggleFavoriteResponse>> {
        return flow {
            try {
            emit(BaseUiState.Loading)
            val response = productRepository.toggleFavorite(param)
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    emit(BaseUiState.Success(data))
                }
            } else {
                emit(BaseUiState.Error(response.message()))
            }

        } catch (e: Exception) {
            emit(BaseUiState.Error(e.message ?: "Unknown error"))}
        }
    }
}