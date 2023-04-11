package com.maku.newsapiapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maku.newsapiapp.newsbycategory.ui.NewsTabs

@Composable
fun MainNavGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
      composable(
          route = "tabs",
      ) {
          NewsTabs(
              modifier = modifier
          )
      }
      composable(
          "webView",
      ) {

          OpenWebsite()
      }
    }
}
@Composable
fun OpenWebsite() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = "hmm")
    }
}
