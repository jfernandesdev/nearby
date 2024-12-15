package com.jfernandesdev.nearby.ui.component.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jfernandesdev.nearby.R
import com.jfernandesdev.nearby.ui.theme.Typography

@Composable
fun NearbyWelcomeHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_nearby_icon),
            contentDescription = "Ícone Nearby"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Boas vindas ao Nearby!",
            style = Typography.headlineLarge
        )
        Text(
            text = "Tenha cupons de vantagem para usar em seus estabelecimentos favoritos.",
            style = Typography.bodyLarge

        )
    }
}