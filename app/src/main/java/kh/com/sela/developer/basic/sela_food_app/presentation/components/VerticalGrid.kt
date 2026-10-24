package kh.com.sela.developer.basic.sela_food_app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import kh.com.sela.developer.basic.sela_food_app.R
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ApiResponse
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.ui.theme.BackGroundCard
import kh.com.sela.developer.basic.sela_food_app.ui.theme.OldGreen
import kh.com.sela.developer.basic.sela_food_app.ui.theme.Orange



@Composable
fun ProductGrid(
    products: ApiResponse,
    onProductClick: (ProductDto) -> Unit,
    modifier: Modifier = Modifier
) {
    val productList = products.data

    val columns = 2
    val rows = (productList.size + columns - 1) / columns

    val cardHeight = 168.dp + 8.dp * 2 + 30.dp + 12.dp
    val spacing = 12.dp
    val contentPadding = 16.dp

    val totalHeight = if (rows > 0) {
        cardHeight * rows +
                spacing * (rows - 1) +
                contentPadding * 2 +
                30.dp // Extra space above the BottomNavigationBar
    } else {
        30.dp
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        userScrollEnabled = false,
        contentPadding = PaddingValues(contentPadding),
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalArrangement = Arrangement.spacedBy(spacing),
        modifier = modifier.height(totalHeight)
    ) {
        items(products.data, key = { it.id }) { product ->
            Card(
                onClick = { onProductClick(product) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier,
                colors = CardDefaults.cardColors(
                    containerColor = BackGroundCard.copy(0.8f)
                )
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(168.dp)
                )
                Column(Modifier

                    .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        product.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 15.sp

                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            tint = Orange,
                            painter = painterResource(id = R.drawable.ic_start), contentDescription = null)
                        Text("4.7", color = Color.Black,fontWeight = FontWeight.Bold,fontSize = 15.sp )
                        Text("(503 review)", color = Color.Gray.copy(0.7f),fontSize = 15.sp )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(30.dp)
                            .width(55.dp)

                            .background(color = OldGreen, shape = RoundedCornerShape(8.dp))
                    ) {
                        Text("$${product.price}", color = Color.White,fontSize = 15.sp)
                    }
                }
            }
        }
    }
}