package com.dev.mahamat.andal_ia.NavControl

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dev.mahamat.andal_ia.screen.FavouriteScreen
import com.dev.mahamat.andal_ia.screen.Home


fun NavGraphBuilder.homeGarph(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.Home.route,
        route = HOME_GRAPH
    ){
        composable(Destinations.HomeScreen.route) {
            Home(navController = navController)
        }
        
    }

}
