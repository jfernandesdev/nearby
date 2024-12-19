package com.jfernandesdev.nearby.ui.screen.market_details

import com.jfernandesdev.nearby.data.model.Rule

data class MarketDetailsUiState(
    val rules: List<Rule>? = null,
    val coupon: String? = null
)