package com.github.arminyousefi.vertlune.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    val route: String

    @Serializable
    object Splash : Route { override val route = "splash" }
    @Serializable
    object Welcome : Route { override val route = "welcome" }
    @Serializable
    object Home : Route { override val route = "home" }
    @Serializable
    object Discover : Route { override val route = "discover" }
    @Serializable
    object Shop : Route { override val route = "shop" }
}