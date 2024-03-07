package com.example.dchat.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: PdsViewModel = koinViewModel()
) {
    val healthUiState = viewModel.healthFlow.collectAsStateWithLifecycle()

    Column {
        val s = healthUiState.value?.version ?: "unavailable"
        Text("health: $s")
    }

}
