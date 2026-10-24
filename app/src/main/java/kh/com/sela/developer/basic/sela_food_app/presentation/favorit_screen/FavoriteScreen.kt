package kh.com.sela.developer.basic.sela_food_app.presentation.favorit_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kh.com.sela.developer.basic.sela_food_app.Util.LoadingScreen
import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.presentation.components.FavoriteItemGrid

@Preview(showBackground = true)
@Composable

fun ScreenFavorite(
    viewModel: FavoriteVM = viewModel()
) {

    val favoriteUiState = viewModel.favoriteUiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getFavorites(1)
    }

    val scrollState = rememberLazyGridState()
    when (val state = favoriteUiState.value) {
        is BaseUiState.Loading -> {
            LoadingScreen()
            println("Loading:==========>")
        }

        is BaseUiState.Success -> {
            println("Success:==========>")
            Box(
                modifier = Modifier


                    .fillMaxSize(),
                contentAlignment = Alignment.TopStart,



                ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                    text = "Favorite", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize().padding(top = 35.dp),
                    state = scrollState,
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),

                    ) {

                    items(state.data.data.size, key = { state.data.data[it].id }) {

                        FavoriteItemGrid(
                            onClik = {
                                println("product_id:==========>$it")
                            },
                            onClikFavorite = {},
                            product = state.data.data[it]
                        )

                    }
                }
            }
        }

        is BaseUiState.Error -> {
            println("Error:==========> ${state.message}")

        }

        else -> {}
    }
}