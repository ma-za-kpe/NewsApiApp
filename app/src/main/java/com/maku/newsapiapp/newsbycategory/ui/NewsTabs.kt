package com.maku.newsapiapp.newsbycategory.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.maku.newsapiapp.newsbycategory.ArticleScreen
import com.maku.newsapiapp.ui.theme.NewsApiAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class) // TODO: move these optIn messages to the build.gradle file
@Composable
fun NewsTabs(
    modifier: Modifier = Modifier,
    viewModel: NewsByCategoryViewModel = hiltViewModel(),
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
    ) {
        // Create references for the composables to constrain
        val (tabs, pager) = createRefs()

        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .constrainAs(tabs) {
                    top.linkTo(parent.top, 8.dp)
                    start.linkTo(parent.start, 8.dp)
                    end.linkTo(parent.end, 8.dp)
                    width = Dimension.fillToConstraints
                }
                .padding(0.dp, 0.dp, 0.dp, 8.dp),
            containerColor = Color.Transparent,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = Color.Transparent
                )
            },
            divider = {
                Divider(
                    thickness = 0.dp,
                    color = Color.Transparent
                )
            }
        ) {
            tabRowItems.forEachIndexed { index, item ->
                val selected = pagerState.currentPage == index
                val backgroundColor = if (selected) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.surface
                }

                val clip = if (selected) {
                    RoundedCornerShape(30.dp)
                } else {
                    RoundedCornerShape(0.dp)
                }

                val textColor = if (selected) {
                    MaterialTheme.colorScheme.onPrimaryContainer
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }

                Tab(
                    selected = selected,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                        }
                    },
                    modifier = Modifier
                        .clip(clip)
                        .background(
                            backgroundColor
                        ),
                    text = {
                        Text(
                            text = item.title,
                            color = textColor,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedContentColor = MaterialTheme.colorScheme.secondary
                )
            }
        }
        HorizontalPager(
            count = tabRowItems.size,
            modifier = modifier
                .constrainAs(pager) {
                    top.linkTo(tabs.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            state = pagerState
        ) { page ->
            tabRowItems[page].screen()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsApiAppTheme {
        NewsTabs()
    }
}

// TODO: move these into there own classes
val tabRowItems = listOf(
    TabRowItem(
        title = "Business",
        screen = {
            ArticleScreen("business")
        }
    ),
    TabRowItem(
        title = "Entertainment",
        screen = {
            ArticleScreen("entertainment")
        }
    ),
    TabRowItem(
        title = "Sports",
        screen = {
            ArticleScreen("sports")
        }
    ),
    TabRowItem(
        title = "General",
        screen = {
            ArticleScreen("general")
        }
    ),
    TabRowItem(
        title = "Health",
        screen = {
            ArticleScreen("health")
        }
    ),
    TabRowItem(
        title = "Science",
        screen = {
            ArticleScreen("science")
        }
    ),
    TabRowItem(
        title = "Technology",
        screen = {
            ArticleScreen("technology")
        }
    )
)

data class TabRowItem(
    val title: String,
    val screen: @Composable () -> Unit
)

