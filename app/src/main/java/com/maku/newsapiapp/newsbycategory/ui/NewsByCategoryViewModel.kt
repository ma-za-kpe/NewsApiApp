package com.maku.newsapiapp.newsbycategory.ui

import android.util.Log
import com.maku.newsapiapp.newsbycategory.domain.GetLocalNews
import com.maku.newsapiapp.newsbycategory.domain.GetLocalNewsByCategory
import com.maku.newsapiapp.newsbycategory.domain.GetNetworkNews
import com.maku.newsapiapp.ui.vm.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class NewsByCategoryViewModel @Inject constructor(
    getNews: GetLocalNews, getNetworkNews: GetNetworkNews,
    val getLocalNewsByCategory: GetLocalNewsByCategory
) : MainViewModel(getNews, getNetworkNews) {

    private val _state = MutableStateFlow(ArticlesViewState())
    val state: StateFlow<ArticlesViewState> = _state.asStateFlow()

    fun getNewsArticlesBCategory(category: String) {
        launchCatching {
            getLocalNewsByCategory(category)
                .collect {
                    Log.d("TAG", "articleState categoryText: 5${it}")
                    _state.update { oldState ->
                        oldState.copy(
                            loading = false,
                            shared = it
                        )
                    }
                }
        }
    }
}