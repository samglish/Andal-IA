package com.dev.mahamat.andal_ia.NavControl

import android.content.Context
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dev.mahamat.andal_ia.screen.regist

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.registGraph(
    navController: NavHostController,
    context: Context
){
    navigation(
        startDestination = Screen.regist.route,
        route = REGISTRATION_GRAPH
    ){
        composable(Screen.regist.route){
            regist(navController = navController,context)
        }
    }
}