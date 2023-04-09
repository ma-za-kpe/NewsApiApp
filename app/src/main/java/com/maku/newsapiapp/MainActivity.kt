package com.maku.newsapiapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maku.newsapiapp.newsbycategory.ui.ArticleUiState
import com.maku.newsapiapp.newsbycategory.ui.NewsArticlesApp
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maku.newsapiapp.newsbycategory.ui.NewsByCategoryViewModel
import com.maku.newsapiapp.ui.theme.NewsApiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsApiAppTheme {
                NewsArticlesApp()
            }
        }
    }
}
