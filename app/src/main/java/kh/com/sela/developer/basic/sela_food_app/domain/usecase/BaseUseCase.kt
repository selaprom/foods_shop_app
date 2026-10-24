package kh.com.sela.developer.basic.sela_food_app.domain.usecase

import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kotlinx.coroutines.flow.Flow


abstract class BaseUseCase <in Param,out Result> {
    suspend operator fun invoke(param: Param): Result {
        return execute(param)
    }

    abstract suspend fun execute(param: Param): Result

}