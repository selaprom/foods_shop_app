package kh.com.sela.developer.basic.sela_food_app.presentation.profile_screen

import android.view.Surface
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import itemshow
import kh.com.sela.developer.basic.sela_food_app.R
import kh.com.sela.developer.basic.sela_food_app.presentation.components.itemInfo
import kh.com.sela.developer.basic.sela_food_app.ui.theme.OldGreen

@Preview(showBackground = true)
@Composable
fun ScreenProfile() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Profile", fontWeight = FontWeight.Bold, fontSize = 20.sp
        )
        Surface(
            modifier = Modifier

                .fillMaxWidth(),
            color = Color.White,
            shape = RoundedCornerShape(16.dp),
            shadowElevation = 5.dp

        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(

                    modifier = Modifier.size(75.dp),
                    color = OldGreen,
                    shape = CircleShape,

                    ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(8.dp)


                    )

                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier

                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "Ly Hour", fontWeight = FontWeight.Bold, fontSize = 22.sp)
                    Text(text = "lyhour@gmail.com", fontSize = 19.sp)
                    Surface(
                        modifier = Modifier, shape = RoundedCornerShape(8.dp), color = OldGreen,
                        contentColor = Color.White
                    ) {
                        Text(
                            text = "VIP Gold Member",
                            fontSize = 13.sp,
                            modifier = Modifier.padding(all = 8.dp)
                        )
                    }
                }
            }
        }
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            val spacing = 16.dp
            val itemWidth = (maxWidth - spacing * (itemInfo.size - 1)) / itemInfo.size

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing),
                contentPadding = PaddingValues(vertical = 5.dp)
            ) {
                items(itemInfo) { item ->
                    itemshow(
                        modifier = Modifier.width(itemWidth),
                        number = item.number,
                        title = item.title,
                        subtitle = item.subtitle
                    )
                }
            }
        }
    }
}
