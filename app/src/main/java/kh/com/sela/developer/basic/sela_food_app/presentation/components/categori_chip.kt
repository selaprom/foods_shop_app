package kh.com.sela.developer.basic.sela_food_app.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.sela.developer.basic.sela_food_app.ui.theme.OldGreen

@Composable
fun CategoryChip(
    onSelected: (String) -> Unit = {},
    categoryLabel: List<String> = listOf("All", "Food", "Drink", "Dessert")
) {
    var currentSelected by remember { mutableStateOf("All") }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
            ,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,


    ) {
        itemsIndexed(categoryLabel) { index, label ->
            val selected = label == currentSelected

            AssistChip(
                modifier = Modifier.height(50.dp),
                onClick = { currentSelected = label
                          onSelected(label)
                          },
                shape = RoundedCornerShape(15.dp),
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = if (selected) OldGreen
                    else MaterialTheme.colorScheme.surface,
                    labelColor = if (selected) OldGreen
                    else MaterialTheme.colorScheme.onSurface,
                ),
                border = BorderStroke(
                    width = 2.dp,
                    brush = if (selected)
                        Brush.horizontalGradient(listOf(OldGreen, OldGreen.copy(alpha = 0.5f)))
                    else
                        SolidColor(Color.LightGray)
                ),
                label = { Text(label, color = if(selected)Color.White else OldGreen, fontSize = 15.sp) },
            )
        }
    }
}