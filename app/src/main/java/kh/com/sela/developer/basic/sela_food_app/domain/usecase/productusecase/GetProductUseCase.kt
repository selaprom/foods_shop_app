package kh.com.sela.developer.basic.sela_food_app.domain.usecase.productusecase

import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ApiResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.domain.repository.ProductRepository
import kh.com.sela.developer.basic.sela_food_app.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val productRepository: ProductRepository) :
    BaseUseCase<Unit, Flow<BaseUiState<ApiResponse>>>() {
    override suspend fun execute(param: Unit): Flow<BaseUiState<ApiResponse>> {
        return flow {
            try {
                emit(BaseUiState.Loading)
                val response = productRepository.getProducts()
                if (response.isSuccessful) {
                   response.body()?.let { data ->
                       emit(BaseUiState.Success(data))
                   }
                } else {
                    emit(BaseUiState.Error(response.message()))
                }
            }catch (e: IOException) {
                emit(BaseUiState.Error("No internet connection"))
            } catch (e: Exception) {
                emit(BaseUiState.Error(e.message ?: "Unknown error"))
            }

        }
    }
}