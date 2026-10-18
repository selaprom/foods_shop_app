package kh.com.sela.developer.basic.sela_food_app.presentation.product_detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import kh.com.sela.developer.basic.sela_food_app.R
import kh.com.sela.developer.basic.sela_food_app.data.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.ui.theme.OldGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenProductDetails(
    product: ProductDto? = null,
    onBack: () -> Unit,

    onBuyNow: (ProductDto, Int) -> Unit
){
    val gradient= Brush.verticalGradient(listOf(OldGreen, Color.White))
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(OldGreen, Color.White)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),

        ) {
            AsyncImage(
                model = product?.imageUrl,
                contentDescription = product?.name,
                contentScale = ContentScale.FillWidth,

                modifier = Modifier

                    .fillMaxWidth()
                    .height(450.dp)
                    .clip(RoundedCornerShape(topStart = 80.dp, topEnd = 80.dp))
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 24.dp)


            )





        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .align(Alignment.TopCenter)
        ){
            CircleIconButton(
                onClick = onBack,
                icon = R.drawable.ic_back,
                modifier = Modifier.align(Alignment.CenterStart)
            )

            Text(
                text = "Details",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun CircleIconButton(onClick: () -> Unit,icon: Int,modifier: Modifier = Modifier){
    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = Color.White,
        shadowElevation = 4.dp,
        modifier = Modifier.size(50.dp)
    ){
        Box (contentAlignment = Alignment.Center){
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Back",
                modifier = Modifier.size(24.dp)
            )

        }
    }
}

@Composable
@Preview(showBackground = true)
fun ScreenProductDetailsPreview(){
    ScreenProductDetails(
        product = null,
        onBack = {},
        onBuyNow ={sf,dfs->}
    )

}