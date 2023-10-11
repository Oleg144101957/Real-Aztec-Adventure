package com.arbtemey.solo.aztecgold2.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arbtemey.solo.aztecgold2.AztecViewModel


@Composable
fun NavigationHub(aztecViewModel: AztecViewModel){

    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = NavRoutes.DestinationPrepearing.destination
    ){

        composable(route = NavRoutes.DestinationPrepearing.destination){
            Prepearing(navHostController)
        }

        composable(route = NavRoutes.DestinationLauncher.destination){
            Launcher(navHostController,aztecViewModel)
        }

        composable(route = NavRoutes.DestinationPlayScreen.destination){
            PlayScreen(navHostController, aztecViewModel)
        }
    }
}