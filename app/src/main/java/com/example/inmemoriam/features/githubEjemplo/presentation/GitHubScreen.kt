package com.example.inmemoriam.features.githubEjemplo.presentation

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import androidx.compose.foundation.layout.size
import com.example.inmemoriam.features.githubEjemplo.presentation.GitHubViewModel

@Composable
fun GitHubScreen(modifier: Modifier,
                 vm : GitHubViewModel = koinViewModel())  {
    var nickname by remember{ mutableStateOf("") }
    val state by vm.state.collectAsState()
    Column{
        Text("Ingrese el usuario de github")
        OutlinedTextField(
            value = nickname,
            onValueChange = {
                it -> nickname = it
            }
        )
        OutlinedButton(
            onClick = {
                vm.fetchAlias(nickname)
            }) {
            Text(text = "")
        }
        when( val st = state) {
            is GitHubViewModel.GithubStateUI.Error -> {
                Text(st.message )
            }
            GitHubViewModel.GithubStateUI.Init -> {
                Text("Init" )
            }
            GitHubViewModel.GithubStateUI.Loading -> {
                Text("Loading" )
            }
            is GitHubViewModel.GithubStateUI.Success -> {
                Text(st.github.nickname )
                AsyncImage(
                    model = st.github.pathUrl,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop,
                )
                Text(st.github.pathUrl)
            }
        }

    }
}