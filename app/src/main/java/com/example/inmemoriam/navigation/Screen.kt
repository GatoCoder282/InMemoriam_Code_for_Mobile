package com.example.inmemoriam.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Github: Screen("github")
    object Profile: Screen("profile")
}