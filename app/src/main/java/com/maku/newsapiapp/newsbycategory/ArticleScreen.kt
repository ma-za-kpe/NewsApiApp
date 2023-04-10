package com.maku.newsapiapp.newsbycategory

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maku.newsapiapp.core.domain.model.DomainArticle
import com.maku.newsapiapp.newsbycategory.ui.ArticleUiState
import com.maku.newsapiapp.newsbycategory.ui.ArticlesViewState
import com.maku.newsapiapp.newsbycategory.ui.NewsByCategoryViewModel
import com.maku.newsapiapp.ui.theme.NewsApiAppTheme

@Composable
fun BusinessArticleScreen(
    category: String,
    viewModel: NewsByCategoryViewModel = hiltViewModel(),
) {
    val articleViewState by viewModel.state.collectAsStateWithLifecycle()
    LazyColumn {
        if (articleViewState.loading){
            item {
                CircularProgressIndicator()
            }
        }
        if (articleViewState.business.isEmpty()){
            viewModel.fetchNetworkData(category)
        }
        listOfArticles(articleViewState.business)
    }
}

@Composable
fun EntertainmentArticleScreen(
    category: String,
    viewModel: NewsByCategoryViewModel = hiltViewModel(),
) {
    val articleViewState by viewModel.state.collectAsStateWithLifecycle()
    LazyColumn {
        if (articleViewState.loading){
            item {
                CircularProgressIndicator()
            }
        }
        if (articleViewState.entertainment.isEmpty()){
            Log.d("TAG", "ArticleList: entertainment is empty")
            viewModel.fetchNetworkData(category)
        }
        listOfArticles(articleViewState.entertainment)
    }
}

@Composable
fun SportsArticleScreen(
    category: String,
    viewModel: NewsByCategoryViewModel = hiltViewModel(),
) {
    val articleViewState by viewModel.state.collectAsStateWithLifecycle()
    LazyColumn {
        if (articleViewState.loading){
            item {
                CircularProgressIndicator()
            }
        }
        if (articleViewState.sports.isEmpty()){
            Log.d("TAG", "ArticleList: entertainment is empty")
            viewModel.fetchNetworkData(category)
        }
        listOfArticles(articleViewState.sports)
    }
}

@Composable
fun GeneralArticleScreen(
    category: String,
    viewModel: NewsByCategoryViewModel = hiltViewModel(),
) {
    val articleViewState by viewModel.state.collectAsStateWithLifecycle()
    LazyColumn {
        if (articleViewState.loading){
            item {
                CircularProgressIndicator()
            }
        }
        if (articleViewState.general.isEmpty()){
            viewModel.fetchNetworkData(category)
        }
        listOfArticles(articleViewState.general)
    }
}

@Composable
fun HealthArticleScreen(
    category: String,
    viewModel: NewsByCategoryViewModel = hiltViewModel(),
) {
    val articleViewState by viewModel.state.collectAsStateWithLifecycle()
    LazyColumn {
        if (articleViewState.loading){
            item {
                CircularProgressIndicator()
            }
        }
        if (articleViewState.health.isEmpty()){
            viewModel.fetchNetworkData(category)
        }
        listOfArticles(articleViewState.health)
    }
}

@Composable
fun ScienceArticleScreen(
    category: String,
    viewModel: NewsByCategoryViewModel = hiltViewModel(),
) {
    val articleViewState by viewModel.state.collectAsStateWithLifecycle()
    LazyColumn {
        if (articleViewState.loading){
            item {
                CircularProgressIndicator()
            }
        }
        if (articleViewState.science.isEmpty()){
            viewModel.fetchNetworkData(category)
        }
        listOfArticles(articleViewState.science)
    }
}

@Composable
fun TechnologyArticleScreen(
    category: String,
    viewModel: NewsByCategoryViewModel = hiltViewModel(),
) {
    val articleViewState by viewModel.state.collectAsStateWithLifecycle()
    LazyColumn {
        if (articleViewState.loading){
            item {
                CircularProgressIndicator()
            }
        }
        if (articleViewState.technology.isEmpty()){
            viewModel.fetchNetworkData(category)
        }
        listOfArticles(articleViewState.technology)
    }
}

private fun LazyListScope.listOfArticles(articles: List<DomainArticle>) {
    items(articles) { article ->
        Article(
            title = article.title,
            date = article.publishedAt,
            url = article.url
        )
    }
}

@Composable
fun Article(
    title: String,
    date: String, // TODO: format dat in the domain or data level, such that its saved with the right format in the db
    url: String
) {
    ListItem(
        headlineContent = {
            Text(
                title,
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )
        },
        modifier = Modifier
            .padding(10.dp)
            .border(
                border = BorderStroke(
                    2.dp,
                    MaterialTheme.colorScheme.primaryContainer
                ),
                shape = RoundedCornerShape(12.dp)
            ),
        overlineContent = {
            Text(
                date,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
            )
        },
        trailingContent = {
            // TODO: open the website when a user clicks this link
            Icon(
                Icons.Filled.Link,
                contentDescription = "Localized description"
            )
        },
        colors = ListItemDefaults.colors(),
        tonalElevation = 8.dp
    )
}

@Preview(showBackground = true)
@Composable
fun ArticlePreview() {
    NewsApiAppTheme {
        Article(
            title = "Bonzi Avanza En Una Lluviosa Marrakech - ATP Tour",
            date = "2023-04-05T20:24:14Z",
            url = "https://news.google.com/rss/articles/CBMiPmh0dHBzOi8vd3d3LmF0cHRvdXIuY29tL2VzL25ld3MvYm9uemktbWFycmFrZWNoLTIwMjMtd2VkbmVzZGF50gEA?oc=5"
        )
    }
}

