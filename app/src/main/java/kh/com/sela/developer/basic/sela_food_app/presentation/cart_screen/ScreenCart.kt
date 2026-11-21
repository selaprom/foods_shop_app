package kh.com.sela.developer.basic.sela_food_app.presentation.cart_screen

import android.R.style.Theme
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
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
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FixedScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
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
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun ScreenCart(
    onBack: () -> Unit,
    cartVM: CartViewModel = hiltViewModel(),
    onCheckoutSuccess: () -> Unit
) {
    val uiState by cartVM.uiState.collectAsStateWithLifecycle()

    val snackbar = remember { SnackbarHostState() }
    var showConfirm by remember { mutableStateOf(false) }
    var showSuccess by remember { mutableStateOf(false) }



    LaunchedEffect(Unit) {
        cartVM.load()

    }
    LaunchedEffect(Unit) {
        cartVM.events.collect { event ->
            when (event) {
                CartEvent.CheckoutSuccess -> showSuccess = true
                is CartEvent.ShowMessage -> snackbar.showSnackbar(event.msg)
            }
        }
    }


    when {
        uiState.isLoading && uiState.items.isEmpty() -> {
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
        uiState.error != null && uiState.items.isEmpty()  -> {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(uiState.error ?: "Something went wrong", color = Color.Red)
                Spacer(Modifier.height(8.dp))
                TextButton(onClick = { cartVM.load() }) { Text("Retry") }
            }
        }
        uiState.items.isEmpty() -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
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
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center

                    ) {
                        Text("Cart is empty", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }








                }
            }


        }
        else -> {

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
                            ItemCart(itemCart,
                                onIncrease = {id-> cartVM.increase(id)},
                                onDelete = {id->cartVM.remove(id)},
                                onDecrease = {id->cartVM.decrease(id)}

                            )


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
                            Text(text = String.format("%.2f", uiState.subtotal),fontWeight = FontWeight.Bold,fontSize = 20.sp)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Text("Delivery Fee")
                            Text("${uiState.deliveryFee}",fontWeight = FontWeight.Bold,fontSize = 20.sp)
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
                            Text(text = String.format("%.2f", uiState.grandTotal),fontWeight = FontWeight.Bold,fontSize = 25.sp)
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        FilledTonalButton(
                            modifier = Modifier.fillMaxWidth().height(55.dp).imePadding(),
                            onClick = {
                                showConfirm = true
                            },
                            shape = RoundedCornerShape(32.dp),
                            colors = ButtonDefaults.filledTonalButtonColors(OldGreen, contentColor = Color.White),


                            ) {
                            Text("Checkout",fontWeight = FontWeight.Bold,fontSize = 20.sp)
                        }
                    }
                    SnackbarHost(snackbar, Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp))
                }
                if (showSuccess) {
                    AlertDialog(
                        onDismissRequest = {},
                        title = { Text("Order placed") },
                        text = { Text("Thank you! Your order is on its way.") },
                        confirmButton = {
                            TextButton(onClick = { showSuccess = false; onCheckoutSuccess() }) { Text("OK") }
                        }
                    )
                }
                if (showConfirm) {
                    AlertDialog(
                        onDismissRequest = { showConfirm = false },
                        title = { Text("Confirm order", fontWeight = FontWeight.Bold) },
                        text = { Text("Total: $${"%.2f".format(uiState.grandTotal)}", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                        confirmButton = {
                            TextButton(onClick = {
                                showConfirm = false;
                                cartVM.checkout()
                            }) { Text("Place order",fontWeight = FontWeight.Bold,fontSize = 18.sp) }
                        },
                        dismissButton = {
                            TextButton(onClick = { showConfirm = false }) { Text("Cancel",fontWeight = FontWeight.Bold,fontSize = 18.sp) }
                        }
                    )
                }


            }



        }
    }


}

@Composable
fun SwipeToRevealDelete(
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
    actionWidth: Dp = 80.dp,
    content: @Composable () -> Unit
) {
    val actionWidthPx = with(LocalDensity.current) { actionWidth.toPx() }
    val offsetX = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    Box(modifier = modifier.fillMaxWidth()) {
        // Delete button, only drawn while swiped
        if (offsetX.value < 0f) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .padding(horizontal = 10.dp)
                    .background(Color.Red, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.CenterEnd
            ) {
                IconButton(
                    onClick = {
                        onDelete()
                        scope.launch { offsetX.snapTo(0f) }
                    },
                    modifier = Modifier
                        .width(actionWidth)
                        .fillMaxHeight()
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
                }
            }
        }

        // Your card, draggable horizontally
        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        scope.launch {
                            offsetX.snapTo((offsetX.value + delta).coerceIn(-actionWidthPx, 0f))
                        }
                    },
                    onDragStopped = {
                        val target = if (offsetX.value < -actionWidthPx / 2) -actionWidthPx else 0f
                        offsetX.animateTo(target)
                    }
                )
        ) {
            content()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCart(
    itemCart: CartItem,
    onIncrease: (Int) -> Unit,
    onDecrease: (Int) -> Unit,
    onDelete: (Int) -> Unit

) {


    SwipeToRevealDelete(onDelete = { onDelete(itemCart.id) }) {
        Surface(
            modifier = Modifier.height(107.dp).padding(horizontal = 10.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Surface(
                modifier = Modifier
                    .height(107.dp)
                    .padding(horizontal = 10.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    AsyncImage(
                        model = itemCart.imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .size(85.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(vertical = 4.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            itemCart.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )

                        Text(itemCart.category, fontSize = 15.sp)
                        Text(itemCart.description, fontSize = 15.sp)
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(vertical = 4.dp),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier
                                .height(35.dp)
                                .background(
                                    MaterialTheme.colorScheme.secondary.copy(0.1f),
                                    RoundedCornerShape(16.dp)
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = { onDecrease(itemCart.id) }
                            ) {
                                Icon(
                                    painterResource(R.drawable.ic_minuse),
                                    contentDescription = "Decrease"
                                )
                            }

                            Text("${itemCart.quantity}", fontSize = 18.sp)

                            IconButton(
                                onClick = { onIncrease(itemCart.id) }
                            ) {
                                Icon(
                                    painterResource(R.drawable.ic_add),
                                    contentDescription = "Increase"
                                )
                            }
                        }

                        Text(
                            "$${"%.2f".format(itemCart.total)}",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }



}

