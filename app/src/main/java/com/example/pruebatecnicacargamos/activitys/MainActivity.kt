package com.example.pruebatecnicacargamos.activitys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pruebatecnicacargamos.navigation.AppNavigation
import com.example.pruebatecnicacargamos.screens.MainScreen
import com.example.pruebatecnicacargamos.ui.theme.PruebaTecnicaCargamosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PruebaTecnicaCargamosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(this)
                }
            }
        }
    }
}

/*@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    PruebaTecnicaCargamosTheme {
        AppNavigation()
    }
}*/