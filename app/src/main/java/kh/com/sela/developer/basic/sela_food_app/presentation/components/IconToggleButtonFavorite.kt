package kh.com.sela.developer.basic.sela_food_app.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kh.com.sela.developer.basic.sela_food_app.R

@Composable
fun IconToggleButtonFavorite(
    onClick: () -> Unit,
    color: Color? = null,
    backgroundColor: Color? = null,
    isFavorite: Boolean
) {
    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = backgroundColor ?: Color.White,
        shadowElevation = 4.dp,
        modifier = Modifier.size(45.dp).padding(end = 10.dp, top = 10.dp)

    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(if (isFavorite) R.drawable.ic_favorit else R.drawable.ic_unfavorit),
                contentDescription = "Back",
                modifier = Modifier.size(25.dp),
                tint = color ?: Color.Black
            )

        }
    }
}