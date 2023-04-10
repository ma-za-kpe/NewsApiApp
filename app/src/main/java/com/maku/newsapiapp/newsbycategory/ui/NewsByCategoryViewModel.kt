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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@HiltViewModel
class NewsByCategoryViewModel @Inject constructor(
    val getNews: GetLocalNews,
    val getNetworkNews: GetNetworkNews
) : MainViewModel() {

    private val _state = MutableStateFlow(ArticlesViewState())
    val state: StateFlow<ArticlesViewState> = _state.asStateFlow()

    // TODO: by creating categoryUiState, im attempting to maintain the dynamic nature of the app, all while keeping the code in the viewmodel dry, but im sure there is a better way to handle this matter
    var categoryUiState = mutableStateOf(CategoryUiState())
        private set

    val categoryTitle
        get() = categoryUiState.value.title

    fun onCategorySelected(newValue: String) {
        Log.d("TAG", "articleState categoryText: 3$categoryTitle")
        categoryUiState.value = categoryUiState.value.copy(title = newValue)
    }

    init {
        // TODO: replace this code with the logic in TODO: 7
        subscribeToDB()
    }

    private fun subscribeToDB() {
       launchCatching {
           getNews()
               .collect {
                   when (categoryTitle.toLowerCase()) {
                       "business" -> {
                           _state.update { oldState ->
                               oldState.copy(loading = false, business = it.filter { it.category ==   "business"})
                           }
                       }
                       "entertainment" -> {
                           _state.update { oldState ->
                               oldState.copy(loading = false, entertainment = it.filter { it.category ==  "entertainment"})
                           }
                       }
                       "sports" -> {
                           _state.update { oldState ->
                               oldState.copy(loading = false, sports = it.filter { it.category ==  "sports" })
                           }
                       }
                       "general" -> {
                           _state.update { oldState ->
                               oldState.copy(loading = false, general = it.filter { it.category ==  "general" })
                           }
                       }
                       "health" -> {
                           _state.update { oldState ->
                               oldState.copy(loading = false, health = it.filter { it.category ==  "health" })
                           }
                       }
                       "technology" -> {
                           _state.update { oldState ->
                               oldState.copy(loading = false, technology = it.filter { it.category ==  "technology" })
                           }
                       }
                       "science" -> {
                           _state.update { oldState ->
                               oldState.copy(loading = false, science = it.filter { it.category ==  "science" })
                           }
                       }
                   }
               }
       }
    }

// TODO 7: this is the approach you should probably go with
//
//    val articleState: StateFlow<ArticleUiState> =
//        getNews(categoryTitle.toLowerCase())
//            .map { domainArticle ->
//                ArticleUiState.Success(domainArticle)
//            }
//            .catch { exception ->
//                SnackbarManager.showMessage(exception.toSnackbarMessage())
//            }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(5_000),
//                initialValue = ArticleUiState.Loading,
//            )

    fun fetchNetworkData(category: String) {
        // An alternative way of error handling
        launchCatching {
            Log.d("TAG", " fetchNetworkData: $category")
            getNetworkNews(category)
        }
    }
}

data class CategoryUiState(
    val title: String = "",
)