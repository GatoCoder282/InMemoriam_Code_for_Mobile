package com.example.inmemoriam.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inmemoriam.features.dollar.presentation.DollarScreen
import com.example.inmemoriam.features.githubEjemplo.presentation.GithubScreen
import com.example.inmemoriam.ui.components.CardScreen
import com.example.inmemoriam.features.loginejemplo.presentation.LoginScreen
import com.example.inmemoriam.features.movie.presentation.PopularMoviesScreen
import com.example.inmemoriam.features.profile.application.ProfileScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Dollar.route
    ) {
        composable(Screen.Dollar.route) {
            DollarScreen()  // ya configurado
        }

        composable(Screen.Movie.route) {
            PopularMoviesScreen()  // pantalla de películas
        }

        composable(Screen.Profile.route) {
            ProfileScreen()  // pantalla de perfil
        }

        composable(Screen.Github.route) {
            GithubScreen(modifier = Modifier)
        }

        composable(Screen.Home.route) {
            // aquí podrías agregar un Dashboard o pantalla principal
        }
    }
}
