package com.dev.mahamat.andal_ia.NavControl


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.ChatBubble
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector


const val REGISTRATION_GRAPH = "regist"
const val AUTH_GRAPH = "auth"
const val HOME_GRAPH = "home"
const val ROOT_GRAPH = "root"

sealed class Screen(val route : String){

    object Login : Screen(route = "login_screen")
    object Home : Screen(route =  "home_screen")
    object regist : Screen(route = "info_screen")
    object Splash : Screen(route = "splash_screen")


}

sealed class Destinations(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object HomeScreen : Destinations(
        route = "home_screen",
        title = "Aceuil",
        icon = Icons.Outlined.Home
    )

    object Chat : Destinations(
        route = "chat_screen",
        title = "Chat",
        icon = Icons.Outlined.ChatBubble
    )

    object VR : Destinations(
        route = "virtual_Reality_screen",
        title = "VR",
        icon = Icons.Outlined.Calculate
    )

    object Setting : Destinations(
        route = "setting_screen",
        title = "Parametre",
        icon = Icons.Outlined.Settings
    )

}
