package com.example.inmemoriam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.inmemoriam.features.githubEjemplo.presentation.GitHubScreen
import com.example.inmemoriam.navigation.AppNavigation
import com.example.inmemoriam.ui.theme.InMemoriamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InMemoriamTheme {
                /*Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    GitHubScreen(modifier = Modifier.padding(paddingValues = innerPadding))
                }*/
                AppNavigation()
            }
        }
    }
}

