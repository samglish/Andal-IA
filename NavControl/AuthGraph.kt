package com.dev.mahamat.andal_ia.NavControl

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dev.mahamat.andal_ia.screen.login

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    context: Context
){
    navigation(
        startDestination = Screen.Login.route,
        route = AUTH_GRAPH
    ){
        composable(Screen.Login.route){
            login(navController = navController,context)
        }
    }

}