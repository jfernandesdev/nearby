package com.jfernandesdev.nearby

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jfernandesdev.nearby.data.model.Market
import com.jfernandesdev.nearby.ui.route.Home
import com.jfernandesdev.nearby.ui.route.Location
import com.jfernandesdev.nearby.ui.route.QrCodeScanner
import com.jfernandesdev.nearby.ui.route.Splash
import com.jfernandesdev.nearby.ui.route.Welcome
import com.jfernandesdev.nearby.ui.screen.home.HomeScreen
import com.jfernandesdev.nearby.ui.screen.home.HomeViewModel
import com.jfernandesdev.nearby.ui.screen.location.LocationScreen
import com.jfernandesdev.nearby.ui.screen.market_details.MarketDetailsScreen
import com.jfernandesdev.nearby.ui.screen.market_details.MarketDetailsUiEvent
import com.jfernandesdev.nearby.ui.screen.market_details.MarketDetailsViewModel
import com.jfernandesdev.nearby.ui.screen.qrcode_scanner.QrCodeScannerScreen
import com.jfernandesdev.nearby.ui.screen.splash.SplashScreen
import com.jfernandesdev.nearby.ui.screen.welcome.WelcomeScreen
import com.jfernandesdev.nearby.ui.theme.NearbyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearbyTheme {
                val navController = rememberNavController()

                val homeViewModel by viewModels<HomeViewModel>()
                val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

                val marketDetailsViewModel by viewModels<MarketDetailsViewModel>()
                val marketDetailsUiState by marketDetailsViewModel.uiState.collectAsStateWithLifecycle()

                NavHost(
                    navController = navController,
                    startDestination = Splash
                ) {
                    composable<Splash> {
                        SplashScreen(
                            onNavigateToWelcome = {
                                navController.navigate(Welcome)
                            }
                        )
                    }
                    composable<Welcome> {
                        WelcomeScreen(
                            onNavigateToHome = {
                                navController.navigate(Home)
                            }
                        )
                    }
                    composable<Home> {
                        HomeScreen(
                            onNavigationToMarketDetails = { selectedMarket ->
                                navController.navigate(selectedMarket)
                            },
                            uiState = homeUiState,
                            onEvent = homeViewModel::onEvent
                        )
                    }
                    composable<Market> {
                        val selectedMarket = it.toRoute<Market>()

                        val selectedCategory by remember {
                            mutableStateOf(
                                homeUiState.categories?.firstOrNull { category ->
                                    category.id == selectedMarket.categoryId
                                }
                            )
                        }

                        marketDetailsViewModel.onEvent(
                            MarketDetailsUiEvent.OnMarketSelected(
                                market = selectedMarket,
                                category = selectedCategory
                            )
                        )

                        MarketDetailsScreen(
                            market = selectedMarket,
                            category = selectedCategory,
                            uiState = marketDetailsUiState,
                            onEvent = marketDetailsViewModel::onEvent,
                            onNavigateBack = {
                                navController.popBackStack()
                            },
                            onNavigateToQrCodeScanner = {
                                navController.navigate(QrCodeScanner)
                            },
                            onNavigateToLocation = {
                                navController.navigate(Location)
                            }
                        )
                    }
                    composable<QrCodeScanner> {
                        QrCodeScannerScreen(onCompleteScan = { qrCodeContent ->
                            if (qrCodeContent.isNotEmpty()) {
                                marketDetailsViewModel.onEvent(
                                    MarketDetailsUiEvent.OnFetchCoupon(
                                        qrCodeContent = qrCodeContent
                                    )
                                )
                            }
                            navController.popBackStack()
                        })
                    }
                    composable<Location> {
                        val market = marketDetailsUiState.market
                        val category = marketDetailsUiState.category

                        if (market != null && category != null) {
                            LocationScreen(
                                market = market,
                                category = category,
                                onNavigateBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    NearbyTheme {

    }
}