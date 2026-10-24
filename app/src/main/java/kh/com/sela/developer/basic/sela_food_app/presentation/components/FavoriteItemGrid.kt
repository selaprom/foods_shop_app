package kh.com.sela.developer.basic.sela_food_app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.FavoriteDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.ui.theme.BackGroundCard
import kh.com.sela.developer.basic.sela_food_app.ui.theme.BackGroundCard2

@Composable

fun FavoriteItemGrid(
    onClik: (product_id: Int) -> Unit,
    onClikFavorite: () -> Unit,
    product: FavoriteDto
) {
    Box(
        modifier = Modifier.background(
            color = Color.Transparent,
            shape = RoundedCornerShape(16.dp)
        ),
    ) {
        Card(
            modifier = Modifier

                .clickable(onClick = { onClik(product.product?.id!!) }),
            colors = CardDefaults.cardColors(containerColor = BackGroundCard2),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)

                    .background(color = Color.Transparent,
                        shape = RoundedCornerShape(16.dp)),
                verticalArrangement = Arrangement.spacedBy(-3.dp),

                ) {
                AsyncImage(
                    model = product.product?.imageUrl,


                    contentDescription = product.product?.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(shape = RoundedCornerShape(16.dp)),

                    contentScale = ContentScale.Crop,

                    )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "${product.product?.name}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )

                Text(text = "${product.product?.description}", fontSize = 15.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "\$${product.product?.price}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp,
                    color = Color.Black
                )
            }
        }
    }

}
