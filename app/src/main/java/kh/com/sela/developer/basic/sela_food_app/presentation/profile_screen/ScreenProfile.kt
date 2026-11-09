package kh.com.sela.developer.basic.sela_food_app.presentation.profile_screen

import android.view.Surface
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import itemshow
import kh.com.sela.developer.basic.sela_food_app.R
import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.presentation.components.SignOutButton
import kh.com.sela.developer.basic.sela_food_app.presentation.components.itemInfo
import kh.com.sela.developer.basic.sela_food_app.presentation.components.itemService
import kh.com.sela.developer.basic.sela_food_app.presentation.components.ortherServices
import kh.com.sela.developer.basic.sela_food_app.ui.theme.OldGreen

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ScreenProfile(
    viewModel: AccountVM = viewModel(),
    onLogout: () -> Unit = {}
) {
    var isLoading by remember { mutableStateOf(false) }
    val logoutUiState by viewModel.logoutUiState.collectAsStateWithLifecycle()
    var isShowDialog by remember { mutableStateOf(false) }
    LaunchedEffect(logoutUiState) {
        when (val state = logoutUiState) {
            is BaseUiState.Loading -> {
                isLoading = true
            }

            is BaseUiState.Success -> {
                isLoading = false
                onLogout()
                viewModel.resetState()


            }
            is BaseUiState.Error -> {
                isLoading = false

            }
            else -> {
                isLoading = false
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(12.dp)
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
                    .padding(12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(

                    modifier = Modifier.size(65.dp),
                    color = OldGreen,
                    shape = CircleShape,

                    ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(10.dp)


                    )

                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier

                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(text = "Ly Hour", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "lyhour@gmail.com", fontSize = 17.sp)
                    Surface(
                        modifier = Modifier, shape = RoundedCornerShape(8.dp), color = OldGreen,
                        contentColor = Color.White
                    ) {
                        Text(
                            text = "VIP Gold Member",
                            fontSize = 13.sp,
                            modifier = Modifier.padding(all = 6.dp)
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
        Box(
            modifier = Modifier
                .height(380.dp)
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {

                ortherServices.forEachIndexed { index, item ->
                    var isFirst = index == 0
                    var isLast = index == ortherServices.size - 1

                    itemService(
                        icon = item.icon,
                        title = item.title,
                        isFirst = isFirst,
                        isLast = isLast,
                        onClick = {}
                    )
                }


            }
        }
        //
        SignOutButton(
            onClick = {
                isShowDialog = true
            },
            modifier = Modifier
        )
    }
    if (isLoading) {
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
    if (isShowDialog) {
        Dialog(
            onDismissRequest = { isShowDialog = false }
        ) {
            AnimatedVisibility(
                visible = isShowDialog,
                enter = fadeIn(
                    animationSpec = tween(300)
                ) + scaleIn(
                    initialScale = 0.8f,
                    animationSpec = tween(300)
                ),
                exit = fadeOut(
                    animationSpec = tween(200)
                ) + scaleOut(
                    targetScale = 0.8f,
                    animationSpec = tween(200)
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
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        Text(
                            text = "Log Out",
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = Color.Black
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = "Do You want to logout?",
                            textAlign = TextAlign.Center
                        )

                        Spacer(
                            modifier = Modifier.height(24.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TextButton(
                                modifier = Modifier.padding(horizontal = 30.dp),
                                onClick = {

                                    isShowDialog = false

                                    }) {
                                Text(
                                    text = "Cancel",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            }
                            TextButton(
                                modifier = Modifier.padding(horizontal = 30.dp),
                                onClick = {
                                    viewModel.logout()
                                    isShowDialog = false
                                }) {
                                Text(text = "Ok", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
