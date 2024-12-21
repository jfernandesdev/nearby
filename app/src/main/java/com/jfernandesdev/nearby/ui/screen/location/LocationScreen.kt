package com.jfernandesdev.nearby.ui.screen.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.jfernandesdev.nearby.R
import com.jfernandesdev.nearby.data.model.Category
import com.jfernandesdev.nearby.data.model.Market
import com.jfernandesdev.nearby.data.model.mock.mockCategories
import com.jfernandesdev.nearby.data.model.mock.mockMarkets
import com.jfernandesdev.nearby.ui.component.button.NearbyButton
import com.jfernandesdev.nearby.ui.component.market_detalis.NearbyMarketDetailsCard
import com.jfernandesdev.nearby.ui.theme.GreenExtraLight

@Composable
fun LocationScreen(
    modifier: Modifier = Modifier,
    market: Market,
    onNavigateBack: () -> Unit,
    category: Category? = null
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GreenExtraLight)
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(
                    LatLng(market.latitude, market.longitude), 17f
                )
            },
            uiSettings = MapUiSettings(
                zoomControlsEnabled = false
            )
        ) {

            context.getDrawable(R.drawable.img_pin)?.let {
                Marker(
                    state = MarkerState(position = LatLng(market.latitude, market.longitude)),
                    title = market.name,
                    snippet = market.address,
                    icon = BitmapDescriptorFactory.fromBitmap(
                        it.toBitmap()
                    )
                )
            }
        }


        NearbyButton(
            modifier = Modifier
                .padding(vertical = 42.dp, horizontal = 24.dp)
                .align(Alignment.TopStart),
            iconRes = R.drawable.ic_arrow_left,
            onClick = onNavigateBack
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 42.dp)
                .align(Alignment.BottomCenter)
                .shadow(4.dp, RoundedCornerShape(16.dp)),
        ) {
            NearbyMarketDetailsCard(
                market = market,
                category = category
            )
        }
    }
}

@Preview
@Composable
private fun LocationScreenPreview() {
    LocationScreen(
        market = mockMarkets.first(),
        category = mockCategories.first(),
        onNavigateBack = {}
    )
}