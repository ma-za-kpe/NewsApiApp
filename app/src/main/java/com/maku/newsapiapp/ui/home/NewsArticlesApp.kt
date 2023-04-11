package com.maku.newsapiapp.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maku.newsapiapp.rememberAppState
import com.maku.newsapiapp.ui.navigation.MainNavGraph

@Composable
fun NewsArticlesApp() {
    val appState = rememberAppState()
    androidx.compose.material3.Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = appState.snackbarHostState,
                modifier = Modifier.padding(8.dp),
                snackbar = { snackBarData ->
                    Snackbar(
                        snackBarData,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
        }
    ) {
        MainNavGraph(
            navController = appState.navController,
            startDestination = "tabs",
            appState,
            modifier = Modifier.padding(it)
        )
    }
}