package com.maku.newsapiapp

import android.content.res.Resources
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.maku.newsapiapp.core.utils.snackbar.SnackbarManager
import com.maku.newsapiapp.core.utils.snackbar.SnackbarMessage.Companion.toMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    pagerState: PagerState = rememberPagerState(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
): AppState {
    return remember(
        navController,
        snackbarManager,
        resources,
        coroutineScope,
        pagerState,
        snackbarHostState
    ) {
        AppState(
            navController,
            snackbarManager,
            resources,
            coroutineScope,
            pagerState,
            snackbarHostState
        )
    }
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@Stable
class AppState(
    val navController: NavHostController,
    val snackbarManager: SnackbarManager,
    val resources: Resources,
    val coroutineScope: CoroutineScope,
    val pagerState: PagerState,
    val snackbarHostState: SnackbarHostState
) {

    init {
        coroutineScope.launch {
            snackbarManager.snackbarMessages.filterNotNull().collect { snackbarMessage ->
                val text = snackbarMessage.toMessage(resources)
                snackbarHostState.showSnackbar(text)
            }
        }
    }
}
