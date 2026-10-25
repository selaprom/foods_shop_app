package kh.com.sela.developer.basic.sela_food_app.presentation.favorit_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kh.com.sela.developer.basic.sela_food_app.Util.LoadingContent
import kh.com.sela.developer.basic.sela_food_app.Util.LoadingScreen
import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.request.DeleteFavoriteParams
import kh.com.sela.developer.basic.sela_food_app.presentation.components.FavoriteItemGrid
import kh.com.sela.developer.basic.sela_food_app.ui.theme.OldGreen

@Preview(showBackground = true)
@Composable
fun ScreenFavorite(
    viewModel: FavoriteVM = viewModel()
) {
    val favoriteUiState by viewModel.favoriteUiState.collectAsState()
    val deleteFavoritUrState by viewModel.deleteFavoriteUiState.collectAsState()
    val scrollState = rememberLazyGridState()

    var isShowDailog = viewModel.isShowDailog.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getFavorites(1)
    }

    LaunchedEffect(deleteFavoritUrState) {
        when (val state = deleteFavoritUrState) {
            is BaseUiState.Success -> {
                viewModel.getFavorites(1)



            }
            is BaseUiState.Error -> println("Error:==========> ${state.message}")
            else -> {}
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = favoriteUiState) {
            is BaseUiState.Loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            is BaseUiState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    state = scrollState,
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Text(
                            modifier = Modifier.padding(start = 10.dp),
                            text = "Favorite",
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                    }

                    items(
                        items = state.data.data,
                        key = { it.id }
                    ) { item ->
                        FavoriteItemGrid(
                            modifier = Modifier.animateItem(),
                            onClik = { println("product_id:==========>$it") },
                            onClikFavorite = {
                                viewModel.setIsShowDailog(true)
                                item.product?.id?.let { productId ->
                                    viewModel.toggleFavorite(
                                        DeleteFavoriteParams(userId = 1, productId = productId)
                                    )
                                }
                            },
                            product = item
                        )
                    }
                }
            }

            is BaseUiState.Error -> {
                Text("Error: ${state.message}", Modifier.align(Alignment.Center))
            }

            else -> {}
        }

        if (isShowDailog.value) {
            LoadingContent()
        }
    }
}