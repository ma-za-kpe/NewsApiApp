package com.maku.newsapiapp.newsbycategory.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.viewModelScope
import com.maku.newsapiapp.core.utils.snackbar.SnackbarManager
import com.maku.newsapiapp.core.utils.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import com.maku.newsapiapp.newsbycategory.domain.GetLocalNews
import com.maku.newsapiapp.newsbycategory.domain.GetNetworkNews
import com.maku.newsapiapp.ui.vm.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class NewsByCategoryViewModel @Inject constructor(
    getNews: GetLocalNews,
    val getNetworkNews: GetNetworkNews
) : MainViewModel() {

    // TODO: by creating categoryUiState, im attempting to maintain the dynamic nature of the app, all while keeping the code in the viewmodel dry, but im sure there is a better way to handle this matter
    var categoryUiState = mutableStateOf(CategoryUiState())
        private set

    val categoryTitle
        get() = categoryUiState.value.title

    fun onCategorySelected(newValue: String) {
        Log.d("TAG", "categoryText: 3$categoryTitle")
        categoryUiState.value = categoryUiState.value.copy(title = newValue)
    }

    val articleState: StateFlow<ArticleUiState> =
        getNews(categoryTitle.toLowerCase())
            .map {
                ArticleUiState.Success(it)
            }
            .catch { exception ->
                SnackbarManager.showMessage(exception.toSnackbarMessage())
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ArticleUiState.Loading,
            )

    fun fetchNetworkData() {
        // An alternative way of error handling
        launchCatching {
            getNetworkNews(categoryTitle.toLowerCase())
        }
    }
}

data class CategoryUiState(
    val title: String = "",
)