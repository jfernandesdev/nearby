package com.jfernandesdev.nearby.ui.component.market_detalis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jfernandesdev.nearby.R
import com.jfernandesdev.nearby.data.model.Category
import com.jfernandesdev.nearby.data.model.Market
import com.jfernandesdev.nearby.data.model.mock.mockCategories
import com.jfernandesdev.nearby.data.model.mock.mockMarkets
import com.jfernandesdev.nearby.ui.component.category.CategoryFilterChipView.Companion.fromDescription
import com.jfernandesdev.nearby.ui.theme.Gray500
import com.jfernandesdev.nearby.ui.theme.Gray600
import com.jfernandesdev.nearby.ui.theme.GreenBase
import com.jfernandesdev.nearby.ui.theme.RedBase
import com.jfernandesdev.nearby.ui.theme.RedLight
import com.jfernandesdev.nearby.ui.theme.Typography

@Composable
fun NearbyMarketDetailsCard(
    modifier: Modifier = Modifier,
    market: Market,
    category: Category? = null
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(text = market.name, style = Typography.headlineMedium, color = Gray600)

                    category?.name?.let { categoryName ->
                        fromDescription(description = categoryName)?.let { icon ->
                            Icon(
                                modifier = Modifier.size(18.dp),
                                painter = painterResource(icon.icon),
                                tint = GreenBase,
                                contentDescription = "Ícone Categoria"
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(RedLight)
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.ic_ticket),
                        tint = RedBase,
                        contentDescription = "Ícone Cupom"
                    )
                    Text(
                        text = "${market.coupons}",
                        style = Typography.labelMedium,
                        color = Gray600
                    )
                }
            }

            InfoRow(
                icon = painterResource(id = R.drawable.ic_map_pin),
                text = market.address,
                contentDescription = "Ícone Localização"
            )

            InfoRow(
                icon = painterResource(id = R.drawable.ic_phone),
                text = market.phone,
                contentDescription = "Ícone Telefone"
            )
        }
    }
}

@Composable
fun InfoRow(icon: Painter, text: String, contentDescription: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = icon,
            tint = Gray500,
            contentDescription = contentDescription
        )
        Text(
            text = text,
            style = Typography.labelMedium,
            color = Gray500
        )
    }
}

@Preview
@Composable
private fun NearbyMarketDetailsInfosPreview() {
    NearbyMarketDetailsCard(
        modifier = Modifier.fillMaxWidth(),
        market = mockMarkets.first(),
        category = mockCategories.first()
    )
}