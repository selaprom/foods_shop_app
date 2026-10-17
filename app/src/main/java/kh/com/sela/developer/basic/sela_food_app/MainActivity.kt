package kh.com.sela.developer.basic.sela_food_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import kh.com.sela.developer.basic.sela_food_app.navigation.AppNavigation
import kh.com.sela.developer.basic.sela_food_app.ui.theme.Sela_food_appTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sela_food_appTheme {
                AppNavigation()
            }
        }
    }
}