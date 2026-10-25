package kh.com.sela.developer.basic.sela_food_app.presentation.home_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kh.com.sela.developer.basic.sela_food_app.Util.ErrorScreen
import kh.com.sela.developer.basic.sela_food_app.Util.LoadingScreen
import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ApiResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductByCategoryResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.presentation.components.AutoSlideCards
import kh.com.sela.developer.basic.sela_food_app.presentation.components.CategoryChip
import kh.com.sela.developer.basic.sela_food_app.presentation.components.ItemCard
import kh.com.sela.developer.basic.sela_food_app.ui.theme.OldGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHome(
    viewModel: HomeVM = viewModel(),
    onProductClick: (ProductDto) -> Unit = {},
) {
    var showSearchSheet by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var categoryType = viewModel.categoryType.collectAsState()

    val productUiState = viewModel.productUiState.collectAsState()

    LaunchedEffect(productUiState) {
        viewModel.getProducts()

    }


    when (val state = productUiState.value) {
        is BaseUiState.Loading -> {
            LoadingScreen()
        }

        is BaseUiState.Success -> {
            val products = state.data
            val filteredProducts = when (categoryType.value) {
                "All" -> {
                    products.data
                }
                "Food"->{
                    products.data.filter { product ->

                        product.category.equals("Food", ignoreCase = true)
                    }
                }
                "Drink" -> {
                    products.data.filter { product ->
                        product.category.equals("Drink", ignoreCase = true)
                    }
                }
                "Dessert" -> {
                    products.data.filter { product ->
                        product.category.equals("Dessert", ignoreCase = true)
                    }
                }
                else -> {
                    products.data
                }
            }


            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 10.dp),
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Column(
                        modifier = Modifier
                            .imePadding()
                            .fillMaxSize(),

                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //search
                        Surface(
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                                .padding(horizontal = 5.dp)
                                .fillMaxWidth()
                                .height(47.dp)
                                .clickable {
                                    showSearchSheet = true
                                },
                            shape = RoundedCornerShape(12.dp),
                            color = Color.White,
                            border = BorderStroke(1.8.dp, OldGreen),
                        ) {
                            Row(
                                modifier = Modifier.padding(
                                    horizontal = 16.dp,
                                    vertical = 12.dp
                                ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search",
                                    Modifier.size(26.dp)
                                )

                                Spacer(Modifier.width(12.dp))

                                Text(
                                    fontSize = 20.sp,
                                    text = "Search...",

                                    color = MaterialTheme.colorScheme
                                        .onSurfaceVariant
                                )
                            }
                        }
                        //auto slide
                        AutoSlideCards()
                        //chip
                        CategoryChip(
                            onSelected = {

                                viewModel.setcategorytype(it)


                            },
                            currentSelected = categoryType.value
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "All Products",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                        }


                    }
                }
                items(filteredProducts.size, key = { filteredProducts[it].id }) {
                    ItemCard(
                        product = filteredProducts[it],
                        onProductClick = {
                            onProductClick(it)
                        }
                    )
                }
            }


        }

        is BaseUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetry = {
                    viewModel.getProducts()
                }
            )
        }

        else -> {}
    }



    if (showSearchSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showSearchSheet = false
            },
            modifier = Modifier.imePadding(),
            shape = RoundedCornerShape(
                topStart = 28.dp,
                topEnd = 28.dp
            ),
            containerColor = MaterialTheme.colorScheme.surface,
            dragHandle = {
                BottomSheetDefaults.DragHandle()
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Search",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text("Search anything...")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = null
                            )
                        },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(
                                    onClick = { searchQuery = "" }
                                ) {
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "Clear search"
                                    )
                                }
                            }
                        },
                        shape = RoundedCornerShape(16.dp),
                        singleLine = true
                    )

                    Text(
                        text = "Recent searches",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )


                }


                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 4.dp
                ) {
                    Button(
                        onClick = {

                            showSearchSheet = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 20.dp,
                                end = 20.dp,
                                top = 12.dp,
                                bottom = 16.dp
                            )
                            .height(52.dp),
                        enabled = searchQuery.isNotBlank(),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Search")
                    }
                }
            }
        }
    }


}

