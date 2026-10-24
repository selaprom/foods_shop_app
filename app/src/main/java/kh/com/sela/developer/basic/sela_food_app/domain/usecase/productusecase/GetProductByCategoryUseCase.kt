package kh.com.sela.developer.basic.sela_food_app.domain.usecase.productusecase

import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.data.repository.ProductRepositoryImpl
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ApiResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductByCategoryResponse
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductByCategoryUseCase @Inject constructor(
    private val productRepository: ProductRepositoryImpl
): BaseUseCase<Int, Flow<BaseUiState<ApiResponse>>>() {
    override suspend fun execute(param: Int): Flow<BaseUiState<ApiResponse>> {
        return flow {
            try {
                emit(BaseUiState.Loading)
                val response = productRepository.getProductByCategory(param)
                if (response.isSuccessful) {
                    response.body()?.let { data ->
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