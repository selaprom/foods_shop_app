package kh.com.sela.developer.basic.sela_food_app.presentation.cart_screen

import android.R.style.Theme
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButtonDefaults.colors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FixedScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.sela.developer.basic.sela_food_app.R
import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItem
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.CartItemDto
import kh.com.sela.developer.basic.sela_food_app.domain.model.response.toDomain
import kh.com.sela.developer.basic.sela_food_app.ui.theme.BackGroundCard
import kh.com.sela.developer.basic.sela_food_app.ui.theme.OldGreen

@Composable
fun ScreenCart(
    onBack: () -> Unit,
    cartVM: CartViewModel = hiltViewModel()
) {
    val state by cartVM.state.collectAsStateWithLifecycle()
    var isLoding by remember { mutableStateOf(false) }
    val uiState by cartVM.uiState.collectAsStateWithLifecycle()


    when (val state = state) {
        is BaseUiState.Loading -> {
            isLoding = true
        }
        is BaseUiState.Success -> {
            isLoding = false
            val items = state.data.data.map { it.toDomain() }
            cartVM.setItems(items)   // push the list into the ViewModel
        }

        else -> Unit
    }

    when( state){
        is BaseUiState.Success -> {
            val cartItems = uiState.items


            Box(
                modifier = Modifier
                    .background(BackGroundCard)
                    .fillMaxSize(),
            ) {
                //as topbar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .padding(top = 65.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Surface(
                        modifier = Modifier
                            .clickable(onClick = onBack)
                            .size(46.dp)
                            .align(TopStart),
                        shadowElevation = 4.dp,
                        shape = CircleShape,
                        color = Color.White,
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(24.dp),

                            painter = painterResource(id = R.drawable.ic_back), contentDescription = "Back",
                            tint = OldGreen
                        )
                    }
                    Text(
                        modifier = Modifier.align(Alignment.TopCenter),
                        text = "My Cart", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = OldGreen
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 120.dp)
                        .fillMaxHeight(0.63f)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),

                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(
                            items = cartItems,
                            key = { it.id }
                        ) { itemCart ->
                            ItemCart(itemCart)
                        }
                    }
                }



                //bill & checkout
                Surface(
                    modifier = Modifier
                        .fillMaxHeight(0.32f)
                        .align(Alignment.BottomCenter),
                    color = Color.White,
                    shape = RoundedCornerShape(topStart =32.dp, topEnd = 32.dp),
                ) {
                    Column(
                        modifier = Modifier.padding(top = 25.dp, start = 16.dp, end = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(9.dp)
                    ) {
                        Text("Order Summary", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Text("Sub Total")
                            Text("$24.00",fontWeight = FontWeight.Bold,fontSize = 20.sp)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Text("Delivery Fee")
                            Text("$2.00",fontWeight = FontWeight.Bold,fontSize = 20.sp)
                        }
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 16.dp),
                            thickness = 2.dp
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Text("Total",fontWeight = FontWeight.Bold,fontSize = 25.sp)
                            Text("$26.00",fontWeight = FontWeight.Bold,fontSize = 25.sp)
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        FilledTonalButton(
                            modifier = Modifier.fillMaxWidth().height(55.dp).imePadding(),
                            onClick = {},
                            shape = RoundedCornerShape(32.dp),
                            colors = ButtonDefaults.filledTonalButtonColors(OldGreen, contentColor = Color.White),


                            ) {
                            Text("Checkout",fontWeight = FontWeight.Bold,fontSize = 20.sp)
                        }
                    }
                }

            }
        }else -> {}
    }
    if (isLoding) {
        Dialog(
            onDismissRequest = {}
        ) {
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(tween(300)) +
                        scaleIn(
                            initialScale = 0.85f,
                            animationSpec = tween(300)
                        )
            ) {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color.White
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Loading",
                            style = MaterialTheme.typography.titleLarge
                        )

                        CircularProgressIndicator(
                            color = OldGreen,
                            strokeWidth = 4.dp
                        )

                        Text(
                            text = "Please wait...",
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }

}


@Composable
fun ItemCart(
    itemCart: CartItem,
){
    Surface(
        modifier = Modifier.height(107.dp).padding(horizontal = 10.dp), shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),

        )   {
            AsyncImage(
                model = itemCart.imageUrl,
                contentDescription = "",
                modifier = Modifier

                    .height(100.dp).width(100.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),


                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                alpha = 1.0f,
                colorFilter = null,
                filterQuality = FilterQuality.Medium

            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.fillMaxHeight().padding(vertical = 4.dp),
                verticalArrangement = Arrangement.SpaceBetween){
                Text(itemCart.name, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                Text(
                    text = itemCart.category,
                    fontSize = 15.sp
                )
                Text(itemCart.description, fontSize = 15.sp)


            }
            Row(
                modifier = Modifier.weight(1f)

            ) { }
            Column(
                modifier = Modifier.fillMaxHeight().padding(vertical = 4.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
                ){
                Row(
                    modifier = Modifier
                        .height(35.dp)
                        .background(color = MaterialTheme.colorScheme.secondary.copy(0.1f), shape = RoundedCornerShape(16.dp)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Surface(
                        modifier = Modifier.size(30.dp)
                            .clickable {  }
                            .padding(all = 5.dp),
                        shape = CircleShape,
                    ) {
                        Icon(painter = painterResource(R.drawable.ic_minuse), contentDescription = null)

                    }
                    Spacer(modifier = Modifier.width(5.dp))

                    Text("${itemCart.quantity}", fontSize = 18.sp,color = Color.Black)
                    Spacer(modifier = Modifier.width(5.dp))
                    Surface(
                        modifier = Modifier
                            .clickable{}
                            .size(30.dp)
                            .padding(all = 5.dp),
                        shape = CircleShape,
                    ) {
                        Icon(painter = painterResource(R.drawable.ic_add), contentDescription = null)

                    }

                }
                Text("$${"%.2f".format(itemCart.total)}",fontSize = 19.sp,fontWeight = FontWeight.Bold,color = Color.Black)
            }

        }
    }
}