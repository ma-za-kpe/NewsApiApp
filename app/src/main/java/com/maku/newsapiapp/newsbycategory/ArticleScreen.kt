package com.maku.newsapiapp.newsbycategory

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
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
import com.maku.newsapiapp.newsbycategory.ui.NewsByCategoryViewModel
import com.maku.newsapiapp.ui.theme.NewsApiAppTheme

@Composable
fun ArticleScreen(
    viewModel: NewsByCategoryViewModel = hiltViewModel(),
) {
    val uiState by viewModel.articleState.collectAsStateWithLifecycle()
    ArticleList(
        uiState,
        viewModel::fetchNetworkData
    )
}

@Composable
fun ArticleList(
    uiState: ArticleUiState,
    fetchNetworkData: () -> Unit,
) {

    when (uiState) {
        is ArticleUiState.Loading -> {
            CircularProgressIndicator()
        }
        is ArticleUiState.Success -> {
            if (uiState.articles.isEmpty()) {
                // pass down category here
                // this only runs once, here you can trigger the first network call, howevere
                // TODO: 03, i believe there are better ways to handle initial data loading, eg, using splash screen api, or in an app start up configuration
                fetchNetworkData()
            }
            LazyColumn {
                items(uiState.articles) { article ->
                    Article(
                        title = article.title,
                        date = article.publishedAt,
                        url = article.url
                    )
                }
            }
        }
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

@Preview(showBackground = true)
@Composable
fun ArticleListPreview() {
    NewsApiAppTheme {
        ArticleList(
            ArticleUiState.Success(
                listOf( // TODO: you can also move this out of here and simply reference it
                    DomainArticle(
                        author = "ESPN",
                        title = "James volvió a jugar en Olympiacos, que venció a PAOK - ESPN",
                        description = "",
                        url = "https://news.google.com/rss/articles/CBMiZmh0dHBzOi8vd3d3LmVzcG4uY29tLmFyL2Z1dGJvbC9ub3RhL18vaWQvMTE4NTc4MTYvamFtZXMtdm9sdmlvLWEtanVnYXItZW4tb2x5bXBpYWNvcy1xdWUtdmVuY2lvLWEtcGFva9IBc2h0dHBzOi8vd3d3LmVzcG4uY29tLmFyL2Z1dGJvbC9ub3RhL18vaWQvMTE4NTc4MTYvamFtZXMtdm9sdmlvLWEtanVnYXItZW4tb2x5bXBpYWNvcy1xdWUtdmVuY2lvLWEtcGFvaz9wbGF0Zm9ybT1hbXA?oc=5",
                        urlToImage = "",
                        publishedAt = "2023-04-05T20:36:35Z",
                        source = DomainArticle.Source(
                            id = "google-news",
                            name = "Google News"
                        ),
                        content = ""
                    ),
                    DomainArticle(
                        author = "ESPN",
                        title = "James volvió a jugar en Olympiacos, que venció a PAOK - ESPN",
                        description = "",
                        url = "https://news.google.com/rss/articles/CBMiZmh0dHBzOi8vd3d3LmVzcG4uY29tLmFyL2Z1dGJvbC9ub3RhL18vaWQvMTE4NTc4MTYvamFtZXMtdm9sdmlvLWEtanVnYXItZW4tb2x5bXBpYWNvcy1xdWUtdmVuY2lvLWEtcGFva9IBc2h0dHBzOi8vd3d3LmVzcG4uY29tLmFyL2Z1dGJvbC9ub3RhL18vaWQvMTE4NTc4MTYvamFtZXMtdm9sdmlvLWEtanVnYXItZW4tb2x5bXBpYWNvcy1xdWUtdmVuY2lvLWEtcGFvaz9wbGF0Zm9ybT1hbXA?oc=5",
                        urlToImage = "",
                        publishedAt = "2023-04-05T20:36:35Z",
                        source = DomainArticle.Source(
                            id = "google-news",
                            name = "Google News"
                        ),
                        content = ""
                    ),
                    DomainArticle(
                        author = "ESPN",
                        title = "James volvió a jugar en Olympiacos, que venció a PAOK - ESPN",
                        description = "",
                        url = "https://news.google.com/rss/articles/CBMiZmh0dHBzOi8vd3d3LmVzcG4uY29tLmFyL2Z1dGJvbC9ub3RhL18vaWQvMTE4NTc4MTYvamFtZXMtdm9sdmlvLWEtanVnYXItZW4tb2x5bXBpYWNvcy1xdWUtdmVuY2lvLWEtcGFva9IBc2h0dHBzOi8vd3d3LmVzcG4uY29tLmFyL2Z1dGJvbC9ub3RhL18vaWQvMTE4NTc4MTYvamFtZXMtdm9sdmlvLWEtanVnYXItZW4tb2x5bXBpYWNvcy1xdWUtdmVuY2lvLWEtcGFvaz9wbGF0Zm9ybT1hbXA?oc=5",
                        urlToImage = "",
                        publishedAt = "2023-04-05T20:36:35Z",
                        source = DomainArticle.Source(
                            id = "google-news",
                            name = "Google News"
                        ),
                        content = ""
                    ),
                    DomainArticle(
                        author = "ESPN",
                        title = "James volvió a jugar en Olympiacos, que venció a PAOK - ESPN",
                        description = "",
                        url = "https://news.google.com/rss/articles/CBMiZmh0dHBzOi8vd3d3LmVzcG4uY29tLmFyL2Z1dGJvbC9ub3RhL18vaWQvMTE4NTc4MTYvamFtZXMtdm9sdmlvLWEtanVnYXItZW4tb2x5bXBpYWNvcy1xdWUtdmVuY2lvLWEtcGFva9IBc2h0dHBzOi8vd3d3LmVzcG4uY29tLmFyL2Z1dGJvbC9ub3RhL18vaWQvMTE4NTc4MTYvamFtZXMtdm9sdmlvLWEtanVnYXItZW4tb2x5bXBpYWNvcy1xdWUtdmVuY2lvLWEtcGFvaz9wbGF0Zm9ybT1hbXA?oc=5",
                        urlToImage = "",
                        publishedAt = "2023-04-05T20:36:35Z",
                        source = DomainArticle.Source(
                            id = "google-news",
                            name = "Google News"
                        ),
                        content = ""
                    )
                )
            ),
            {}
        )
    }
}
