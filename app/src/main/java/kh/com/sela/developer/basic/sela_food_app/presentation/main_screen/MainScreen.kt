package kh.com.sela.developer.basic.sela_food_app.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kh.com.sela.developer.basic.sela_food_app.data.response.ProductDto
import kh.com.sela.developer.basic.sela_food_app.presentation.components.BottomNavigationBar
import kh.com.sela.developer.basic.sela_food_app.presentation.favorit_screen.ScreenFavorite
import kh.com.sela.developer.basic.sela_food_app.presentation.home_screen.ScreenHome
import kh.com.sela.developer.basic.sela_food_app.presentation.order_screen.ScreenOrder
import kh.com.sela.developer.basic.sela_food_app.presentation.profile_screen.ScreenProfile
import kh.com.sela.developer.basic.sela_food_app.ui.theme.OldGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onClicktoCart: () -> Unit,
    onSearchClick: () -> Unit,
    onProductClick: (ProductDto) -> Unit
) {
    var itemselected by remember { mutableStateOf<String>("Home") }


    val gradient = Brush.verticalGradient(
        colors = listOf(OldGreen, OldGreen.copy(alpha = 0.6f))
    )
    Scaffold(
        modifier = Modifier
            .background(gradient)
            .fillMaxSize()
            .imePadding(),
        topBar = {
            TopAppBar(
                modifier = Modifier.background(gradient),

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent,
                    actionIconContentColor = Color.White,
                    titleContentColor = Color.White
                ),
                title = {
                    Text(
                        text = "Main Screen",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications"
                        )
                    }

                    IconButton(
                        onClick = onClicktoCart
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Cart"
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                onselectedRoute = itemselected,
                onItemClick = { item ->
                    itemselected = item
                }
            )
        }
    )  { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)


        ) {
            when (itemselected) {
                "Home" -> ScreenHome(onProductClick = {product->
                    onProductClick(product)

                })
                "Favorite" -> ScreenFavorite()
                "Order" -> ScreenOrder()
                "Account" -> ScreenProfile()
            }

        }
    }
}


@Composable
fun SearchBarButton(
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = "Search products...",
                color = Color.Gray
            )
        }
    }
}