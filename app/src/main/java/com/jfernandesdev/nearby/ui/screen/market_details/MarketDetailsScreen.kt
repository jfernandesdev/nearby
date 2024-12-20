package com.jfernandesdev.nearby.ui.screen.market_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jfernandesdev.nearby.R
import com.jfernandesdev.nearby.data.model.Market
import com.jfernandesdev.nearby.data.model.mock.mockMarkets
import com.jfernandesdev.nearby.ui.component.button.NearbyButton
import com.jfernandesdev.nearby.ui.component.market_detalis.NearbyMarketDetailsCoupons
import com.jfernandesdev.nearby.ui.component.market_detalis.NearbyMarketDetailsInfos
import com.jfernandesdev.nearby.ui.component.market_detalis.NearbyMarketDetailsRules
import com.jfernandesdev.nearby.ui.theme.GreenExtraLight
import com.jfernandesdev.nearby.ui.theme.Typography

@Composable
fun MarketDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: MarketDetailsUiState,
    onEvent: (MarketDetailsUiEvent) -> Unit,
    market: Market,
    onNavigateToQrCodeScanner: () -> Unit,
    onNavigateBack: () -> Unit
) {
    LaunchedEffect(true) {
        onEvent(MarketDetailsUiEvent.OnFetchRules(marketId = market.id))
    }

    Box(modifier = modifier
        .fillMaxSize()
        .background(GreenExtraLight)) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            model = market.cover,
            contentDescription = "Imagem do Local",
            contentScale = ContentScale.Crop
        )

        NearbyButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(vertical = 42.dp, horizontal = 24.dp),
            iconRes = R.drawable.ic_arrow_left,
            onClick = onNavigateBack
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .align(Alignment.BottomCenter)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(32.dp)
            ) {
                Column {
                    Text(text = market.name, style = Typography.headlineLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = market.description, style = Typography.bodyLarge)
                }
                Spacer(modifier = Modifier.height(32.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    NearbyMarketDetailsInfos(market = market)
                    HorizontalDivider(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp))

                    if (!uiState.rules.isNullOrEmpty()) {
                        NearbyMarketDetailsRules(rules = uiState.rules)
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                        )
                    }

                    if(!uiState.coupon.isNullOrEmpty()) {
                        NearbyMarketDetailsCoupons(coupons = listOf(uiState.coupon))
                    }
                }


                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NearbyButton(
                        modifier = Modifier.padding(top = 24.dp),
                        iconRes = R.drawable.ic_map_pin,
                        onClick = {}
                    )

                    NearbyButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        iconRes = R.drawable.ic_scan,
                        text = "Ler QR Code",
                        onClick = onNavigateToQrCodeScanner
                    )
                }
            }
        }

    }
}

@Preview
@Composable
private fun MarketDetailsScreenPreview() {
    MarketDetailsScreen(
        market = mockMarkets.first(),
        uiState = MarketDetailsUiState(),
        onEvent = {},
        onNavigateToQrCodeScanner = {},
        onNavigateBack = {}
    )
}