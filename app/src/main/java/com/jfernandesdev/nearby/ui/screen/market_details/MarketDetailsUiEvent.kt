package com.jfernandesdev.nearby.ui.screen.market_details

import com.jfernandesdev.nearby.data.model.Category
import com.jfernandesdev.nearby.data.model.Market

sealed class MarketDetailsUiEvent {
    data class OnFetchRules(val marketId: String): MarketDetailsUiEvent()
    data class OnFetchCoupon(val qrCodeContent: String) : MarketDetailsUiEvent()
    data class OnMarketSelected(val market: Market?, val category: Category?) : MarketDetailsUiEvent()
    data object OnResetCoupon: MarketDetailsUiEvent()
}