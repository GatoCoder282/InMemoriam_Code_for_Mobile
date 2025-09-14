package com.example.inmemoriam.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inmemoriam.features.dollar.presentation.DollarScreen
import com.example.inmemoriam.features.githubEjemplo.presentation.GitHubScreen
import com.example.inmemoriam.ui.components.CardScreen
import com.example.inmemoriam.features.loginejemplo.presentation.LoginScreen

/*
@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.CardEjemplo.route
    ) {
        composable(Screen.Github.route) {
            GitHubScreen(modifier = Modifier)
        }
        composable(Screen.Home.route) {

        }
        composable(Screen.Profile.route) {

        }
        composable (Screen.CardEjemplo.route){
            CardScreen()
        }
    }
}*/

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = Screen.Dollar.route
    ) {
        composable(Screen.Github.route) {
            GitHubScreen(modifier = Modifier)
        }
        composable(Screen.Home.route) {


        }
        composable(Screen.Profile.route) {
            LoginScreen()

        }


        composable(Screen.CardEjemplo.route) { CardScreen() }


        composable(Screen.Dollar.route) {
            DollarScreen()
        }
    }
}
