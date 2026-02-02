package com.github.arminyousefi.vertlune

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.github.arminyousefi.vertlune.ui.navigation.VertluneNavGraph
import com.github.arminyousefi.vertlune.ui.theme.VertluneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VertluneTheme {
                VertluneNavGraph()
            }
        }
    }
}