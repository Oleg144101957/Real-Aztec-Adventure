package com.arbtemey.solo.aztecgold2.ui.screens

sealed class NavRoutes(val destination: String) {

    object DestinationPrepearing : NavRoutes("DestinationPrepearing")
    object DestinationLauncher : NavRoutes("DestinationLauncher")
    object DestinationPlayScreen : NavRoutes("DestinationPlayScreen")
}