package kh.com.sela.developer.basic.sela_food_app.presentation.favorit_screen

import android.widget.Toast
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun ScreenFavorite(
    viewModel: FavoriteVM = viewModel()
) {
    val favoriteUiState by viewModel.favoriteUiState.collectAsState()
    val deleteFavoritUrState by viewModel.deleteFavoriteUiState.collectAsState()
    val scrollState = rememberLazyGridState()
    val listOfselectProduct by viewModel.listOfselectProduct.collectAsState()

    var context = LocalContext.current
    var scope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        viewModel.getFavorites(1)
    }

    LaunchedEffect(Unit) {
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
                if(state.data.data.isEmpty()){
                   Box(modifier = Modifier.align(Alignment.Center)){
                       Text(
                           modifier = Modifier.align(Alignment.Center),
                           text = "No Favorite Product",
                           fontWeight = FontWeight.Bold,
                           fontSize = 20.sp,
                           color = Color.Gray
                       )
                   }
                }else{
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
                            var isFavorite = listOfselectProduct.contains(item.product?.id)
                            FavoriteItemGrid(
                                modifier = Modifier.animateItem(),
                                onClik = { println("product_id:==========>$it") },
                                onClikFavorite = {
                                    viewModel.setListFavoriteProductId(item.product?.id ?: 0)
                                    scope.launch {
                                        Toast.makeText(
                                            context,
                                            if (isFavorite) "Removed From Favorite Complete" else "Add To Favorite Complete",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    item.product?.id?.let { productId ->
                                        viewModel.toggleFavorite(
                                            DeleteFavoriteParams(userId = 1, productId = productId)
                                        )
                                    }

                                },
                                product = item,
                                isFavorite = isFavorite
                            )
                        }
                    }
                }

            }

            is BaseUiState.Error -> {
                Text("Error: ${state.message}", Modifier.align(Alignment.Center))
            }

            else -> {}
        }


    }
}