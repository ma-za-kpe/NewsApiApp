package com.maku.newsapiapp.newsbycategory.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
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

@OptIn(ExperimentalPagerApi::class) // TODO: move these optin messages to the build.gradle file
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

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .constrainAs(tabs) {
                    top.linkTo(parent.top, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    width = Dimension.fillToConstraints
                }
                .padding(15.dp, 0.dp, 15.dp, 0.dp)
                .fillMaxWidth(),
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
                if (selected) {
                    // TODO: Attempting to maintain the dynamic nature of the app, all while keeping the code in the viewmodel dry, but im sure there is a better way to handle this matter
                    viewModel.onCategorySelected(item.title)
                }
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
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedContentColor = MaterialTheme.colorScheme.secondary
                )
            }
        }
        HorizontalPager(
            count = tabRowItems.size,
            modifier = Modifier
                .constrainAs(pager) {
                    top.linkTo(tabs.bottom)
                    start.linkTo(parent.start, 12.dp)
                    end.linkTo(parent.end, 12.dp)
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
        title = "Sports",
        screen = {
            ArticleScreen()
        }
    )
)

data class TabRowItem(
    val title: String,
    val screen: @Composable () -> Unit
)
