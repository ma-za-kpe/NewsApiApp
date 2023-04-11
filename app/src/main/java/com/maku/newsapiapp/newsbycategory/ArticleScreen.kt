package com.maku.newsapiapp.newsbycategory

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maku.newsapiapp.core.domain.model.DomainArticle
import com.maku.newsapiapp.newsbycategory.ui.NewsByCategoryViewModel
import com.maku.newsapiapp.ui.theme.NewsApiAppTheme

@Composable
fun ArticleScreen(
    category: String,
    viewModel: NewsByCategoryViewModel = hiltViewModel(),
) {
    val articleViewState by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(category){
        Log.d("TAG", "articleState categoryText: running")
        viewModel.getNewsArticlesBCategory(category)
    }
    LazyColumn {
        if (articleViewState.loading){
            item {
                CircularProgressIndicator()
            }
        }
        Log.d("TAG", "articleState categoryText: 5${articleViewState.shared}")
        items(articleViewState.shared) { article ->
            Article(
                article
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Article(article: DomainArticle) {
    var expandedState by remember {
        mutableStateOf(false)
    }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )
    Card(
        modifier = Modifier
            .padding(6.dp, 10.dp)
            .animateContentSize(
                animationSpec = TweenSpec(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        border = BorderStroke(
            0.5.dp,
            MaterialTheme.colorScheme.primaryContainer
        ),
        shape = RoundedCornerShape(12.dp),
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                article.publishedAt,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(6f),
                    text = article.title,
                    fontSize =  MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Start,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop Down Arrow"
                    )
                }
            }
            if (expandedState) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(text = "First Text")
                    Text(text = "Second Text")
                }
//                Text(
//                    text = article.title,
//                    fontSize =  FontWeight.Normal,
//                    fontWeight =  FontWeight.Normal,
//                    maxLines = 4,
//                    overflow = TextOverflow.Ellipsis
//                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticlePreview() {
    NewsApiAppTheme {
        Article(
           article = DomainArticle(
               author =  "n-tv NACHRICHTEN",
               content = "",
               description =  "",
               publishedAt = "2023-04-09T22:00:00Z",
               source = DomainArticle.Source(
                   id = "",
                   name = ""
               ),
               title =  "\"Wer wird Millionär?\" als Oster-Sonderausgabe: Vier falsche Antworten verärgern Jauch - n-tv NACHRICHTEN",
               url = "https://news.google.com/rss/articles/CBMihQFodHRwczovL3d3dy5uLXR2LmRlL2xldXRlL3R2L1dlci13aXJkLU1pbGxpb25hZXItYWxzLU9zdGVyLVNvbmRlcmF1c2dhYmUtVmllci1mYWxzY2hlLUFudHdvcnRlbi12ZXJhZXJnZXJuLUphdWNoLWFydGljbGUyNDA0MTA3My5odG1s0gGFAWh0dHBzOi8vYW1wLm4tdHYuZGUvbGV1dGUvdHYvV2VyLXdpcmQtTWlsbGlvbmFlci1hbHMtT3N0ZXItU29uZGVyYXVzZ2FiZS1WaWVyLWZhbHNjaGUtQW50d29ydGVuLXZlcmFlcmdlcm4tSmF1Y2gtYXJ0aWNsZTI0MDQxMDczLmh0bWw?oc=5",
               urlToImage = "",
              category = "sport"
           )
        )
    }
}

