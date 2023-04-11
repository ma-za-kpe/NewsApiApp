package com.maku.newsapiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.maku.newsapiapp.ui.home.NewsArticlesApp
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
