package kh.com.sela.developer.basic.sela_food_app.presentation.components

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.sela.developer.basic.sela_food_app.ui.theme.OldGreen
import kh.com.sela.developer.basic.sela_food_app.ui.theme.OrangeColor

@Composable
fun SignOutButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(80.dp),
        colors = ButtonDefaults.filledTonalButtonColors(contentColor = Color.White,containerColor = OrangeColor),
        shape = RoundedCornerShape(16.dp),
        elevation = ButtonDefaults.filledTonalButtonElevation(defaultElevation = 4.dp),

        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Text("Log Out", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
    }
}