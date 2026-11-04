package kh.com.sela.developer.basic.sela_food_app.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kh.com.sela.developer.basic.sela_food_app.R

data class OrtherService(val icon: Int, val title: String)

val ortherServices = listOf<OrtherService>(
    OrtherService(R.drawable.ic_orders, "My Orders & History"),
    OrtherService(R.drawable.ic_location, "Shopping Address"),
    OrtherService(R.drawable.ic_payment, "Payment Method"),
    OrtherService(R.drawable.ic_notification, "Notification & Deals"),
    OrtherService(R.drawable.ic_security, "Privacy & Security"),
    OrtherService(R.drawable.ic_help, "Help Center & Support")
)


@Composable
fun itemService(
    icon: Int,
    title: String,
    isFirst: Boolean ,
    isLast: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }

            .height(62.dp),
        shape = RoundedCornerShape(topStart = if (isFirst) 16.dp else 0.dp,topEnd = if(isFirst) 16.dp else 0.dp,bottomStart = if (isLast) 16.dp else 0.dp,bottomEnd = if (isLast) 16.dp else 0.dp),
        color = Color.White,
        contentColor = Color.Black,
        shadowElevation = 5.dp,


        ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(painter = painterResource(id = icon), contentDescription = null)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title)
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_next),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )

        }
    }
}