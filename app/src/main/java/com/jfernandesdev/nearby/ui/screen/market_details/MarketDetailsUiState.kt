package com.jfernandesdev.nearby.ui.screen.market_details

import com.jfernandesdev.nearby.data.model.Category
import com.jfernandesdev.nearby.data.model.Market
import com.jfernandesdev.nearby.data.model.Rule

data class MarketDetailsUiState(
    val market: Market? = null,
    val category: Category? = null,
    val rules: List<Rule>? = null,
    val coupon: String? = null
)