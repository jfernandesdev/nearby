package com.jfernandesdev.nearby

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jfernandesdev.nearby.data.model.mock.mockMarkets
import com.jfernandesdev.nearby.ui.screen.HomeScreen
import com.jfernandesdev.nearby.ui.screen.MarketDetailsScreen
import com.jfernandesdev.nearby.ui.theme.NearbyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearbyTheme {
                MarketDetailsScreen(market = mockMarkets.first())
            }
        }
    }
}