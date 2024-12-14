package com.jfernandesdev.nearby.data.model.mock

import com.jfernandesdev.nearby.data.model.Market

val mockMarkets = listOf(
    Market(
        id = "1",
        categoryId = "1",
        name = "Sabor Grill",
        description = "Churrascaria com cortes nobres e buffet variado. Experiência completa para os amantes de carne.",
        coupons = 10,
        rules = emptyList(),
        latitude = -24.8274406,
        longitude = -53.9273784,
        address = "Av. Brasil, 4057 - Centro",
        phone = "(45) 99999-9999",
        cover = "https://images.unsplash.com/photo-1702741168115-cd3d9a682972?q=80&w=1740"
    ),
    Market(
        id = "2",
        categoryId = "2",
        name = "Dom Confeitaria & Café",
        description = "Café aconchegante com opções de lanches e bebidas artesanais. Você merece uma pausa.",
        coupons = 3,
        rules = emptyList(),
        latitude = -24.8254712,
        longitude = -53.9273818,
        address = "R. Dom Pedro II, 1897  - Centro",
        phone = "(45) 99999-8888",
        cover = "https://images.unsplash.com/photo-1469957761306-556935073eba?q=80&w=1658"
    )
)