package kh.com.sela.developer.basic.sela_food_app.presentation.product_detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import kh.com.sela.developer.basic.sela_food_app.R
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.ui.theme.BackGroundCard
import kh.com.sela.developer.basic.sela_food_app.ui.theme.OldGreen
import kh.com.sela.developer.basic.sela_food_app.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenProductDetails(
    product: ProductDto? = null,
    onBack: () -> Unit,

    onBuyNow: (ProductDto, Int) -> Unit
) {
    var isFavorit by remember { mutableStateOf(false) }
    val gradient = Brush.verticalGradient(listOf(OldGreen, Color.White))
    val scrollState = rememberScrollState()
    var numberOrder by remember { mutableStateOf(1) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackGroundCard.copy(1f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)


        ) {
            AsyncImage(
                model = product?.imageUrl,
                contentDescription = product?.name,
                contentScale = ContentScale.Crop,

                modifier = Modifier

                    .fillMaxWidth()
                    .height(450.dp)
                    .clip(RoundedCornerShape(topStart = 80.dp, topEnd = 80.dp))
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp,)


            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .offset(y = (-25).dp),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                color = Color.White,

                ) {
                Column(Modifier.padding(horizontal = 20.dp, vertical = 24.dp)) {

                    //row title
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                    ) {
                        Column(
                            Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = product?.name ?: "",
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp
                            )
                            Row {
                                repeat(5) { i ->
                                    Icon(
                                        Icons.Filled.Star, null,
                                        tint = if (i < 3) Orange else Color(0xFFE0E0E0),
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }

                        }
                        CircleIconButton(
                            onClick = {
                                if (isFavorit) isFavorit = false else isFavorit = true
                            },
                            icon = if (isFavorit) R.drawable.ic_favorit else R.drawable.ic_unfavorit,
                            modifier = Modifier,
                            color = OldGreen
                        )


                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    //row price & add amount
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier,
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text("Total Price")
                            Text(
                                "\$20.99",
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                                color = Color.Black
                            )
                        }
                        Column(
                            modifier = Modifier,
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text("Size")
                            Row(
                                modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                CircleIconButton(
                                    onClick = {
                                        if (numberOrder > 1) numberOrder = numberOrder -1

                                    },
                                    icon = R.drawable.ic_minuse,
                                    modifier = Modifier,

                                    )
                                Text(
                                    "$numberOrder",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp,
                                    color = Color.Black
                                )
                                CircleIconButton(
                                    onClick = {
                                        numberOrder = numberOrder +1
                                    },
                                    icon = R.drawable.ic_add,
                                    modifier = Modifier,
                                    color = Color.White,
                                    backgroundColor = OldGreen

                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    Text("Description", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        """
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                        """.trimIndent(), fontSize = 20.sp
                    )


                }

            }


        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .align(Alignment.TopCenter)
        ) {
            CircleIconButton(
                onClick = onBack,
                icon = R.drawable.ic_back,
                modifier = Modifier.align(Alignment.CenterStart),
                color = OldGreen

            )

            Text(
                text = "Details",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        //button buynow
        Row(
            modifier = Modifier
                .height(95.dp)
                .clickable(onClick = {
                    println("Buy Now")
                })
                .padding(bottom = 30.dp, start = 12.dp, end = 12.dp)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(32.dp))
                .background(OldGreen)
                .fillMaxWidth()
                .padding(horizontal = 12.dp),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Surface(
                onClick = {

                },
                shape = CircleShape,
                color = Color.White,
                contentColor = Color.White,
                shadowElevation = 4.dp,
                modifier = Modifier.size(48.dp).align(Alignment.CenterVertically)
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector =
                            Icons.Default.ShoppingBag,
                        contentDescription = "Favorite",
                        modifier = Modifier.size(24.dp),
                        tint = OldGreen
                    )
                }
            }
            Text("Buy Now", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)
            Icon(
                painter =
                    painterResource(R.drawable.ic_next),
                contentDescription = "Favorite",
                modifier = Modifier.size(30.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
fun CircleIconButton(
    onClick: () -> Unit,
    icon: Int,
    modifier: Modifier = Modifier,
    color: Color? = null,
    backgroundColor: Color? = null
) {
    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = backgroundColor ?: Color.White,
        shadowElevation = 4.dp,
        modifier = Modifier.size(50.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Back",
                modifier = Modifier.size(24.dp),
                tint = color ?: Color.Black
            )

        }
    }
}

@Composable
@Preview(showBackground = true)
fun ScreenProductDetailsPreview() {
    ScreenProductDetails(
        product = null,
        onBack = {},
        onBuyNow = { sf, dfs -> }
    )

}