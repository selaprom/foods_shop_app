package kh.com.sela.developer.basic.sela_food_app.domain.usecase.favoriteusecase

import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.FavoriteResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.domain.repository.FavoriteRepository
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) :
        BaseUseCase<Int, Flow<BaseUiState<FavoriteResponse>>>() {
            override suspend fun execute(param: Int): Flow<BaseUiState<FavoriteResponse>> {
                return flow {

                    try {
                       // emit(BaseUiState.Loading)
                        val response = favoriteRepository.getFavorites(param)
                        if (response.isSuccessful) {
                            response.body()?.let { data ->
                                println("data=========>from usecase $data")
                                emit(BaseUiState.Success(data))
                            }
                        } else {
                            emit(BaseUiState.Error(response.message()))
                        }

                    }catch (e: Exception){
                        emit(BaseUiState.Error(e.message ?: "Unknown error"))
                    }
                }
    }
}
