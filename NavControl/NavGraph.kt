package com.dev.mahamat.andal_ia.NavControl

import android.content.Context
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dev.mahamat.andal_ia.screen.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun navigation(navControleur: NavHostController, context: Context){
    NavHost(
        navController = navControleur,
        startDestination = Screen.Splash.route,
        route = ROOT_GRAPH

    ){
        composable(Screen.Splash.route){
            splashScreen(navController = navControleur)
        }
        authGraph(navControleur,context)
        homeGarph(navControleur)
        registGraph(navControleur,context)
        VRGraph(navControleur,context)
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = Destinations.HomeScreen.route

    ) {
        composable(Destinations.HomeScreen.route) {
            HomeScreen()
        }
        composable(Destinations.Chat.route) {
            FavouriteScreen()
        }
        composable(Destinations.VR.route) {
            VR()
        }
        composable(Destinations.Setting.route) {
            profile(navController)
        }

    }
}