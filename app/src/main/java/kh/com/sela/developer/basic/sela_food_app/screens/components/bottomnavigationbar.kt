package kh.com.sela.developer.basic.sela_food_app.screens.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import kh.com.sela.developer.basic.sela_food_app.ui.theme.OldGreen
import kotlinx.serialization.Serializable

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,

)

val bottomNavItems = listOf(
    BottomNavItem("Home", Icons.Default.Home),
    BottomNavItem("Favorite", Icons.Default.Favorite),
    BottomNavItem("Order", Icons.Default.Receipt),
    BottomNavItem("Account", Icons.Default.Person)
)

@Composable
fun BottomNavigationBar(
    onselectedRoute: Any,
    onItemClick: (String) -> Unit
) {
    NavigationBar {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = onselectedRoute == item.title,
                onClick = { onItemClick(item.title) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        modifier = Modifier.size(if (onselectedRoute == item.title) 28.dp else 24.dp),
                    )
                },
                label = {
                    Text(
                        text = item.title,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = OldGreen,
                    selectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                ),
            )
        }
    }
}
