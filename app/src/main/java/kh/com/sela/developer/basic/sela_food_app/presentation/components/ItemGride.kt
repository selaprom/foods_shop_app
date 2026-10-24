package kh.com.sela.developer.basic.sela_food_app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.ui.theme.BackGroundCard
import kh.com.sela.developer.basic.sela_food_app.ui.theme.BackGroundCard2
import kh.com.sela.developer.basic.sela_food_app.ui.theme.OldGreen
import kh.com.sela.developer.basic.sela_food_app.ui.theme.Orange


@Composable
fun ItemCard(product: ProductDto, onProductClick: (ProductDto) -> Unit){
    Card(
        onClick = { onProductClick(product) },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier,
        colors = CardDefaults.cardColors(containerColor = BackGroundCard2),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),

    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.name,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(132.dp)
        )
        Column(Modifier.background(Color.Transparent)

            .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp)) {
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
                    modifier = Modifier.size(12.dp),
                    tint = Orange,
                    painter = painterResource(id = R.drawable.ic_start), contentDescription = null)
                Text("4.7", color = Color.Black,fontWeight = FontWeight.Bold,fontSize = 12.sp )
                Text("(503 review)", color = Color.Gray.copy(0.7f),fontSize = 12.sp )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(25.dp)
                    .width(50.dp)

                    .background(color = OldGreen, shape = RoundedCornerShape(8.dp))
            ) {
                Text("$${product.price}", color = Color.White,fontSize = 12.sp)
            }
        }
    }

}