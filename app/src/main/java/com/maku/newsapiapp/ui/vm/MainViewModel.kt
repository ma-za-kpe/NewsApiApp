package com.maku.newsapiapp.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maku.newsapiapp.core.utils.snackbar.SnackbarManager
import com.maku.newsapiapp.core.utils.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import com.maku.newsapiapp.domain.usecase.GetLocalNews
import com.maku.newsapiapp.domain.usecase.GetNetworkNews
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Add more log services here, e.g firebase crashlytics, etc
 * */
open class MainViewModel @Inject constructor(
    val getNews: GetLocalNews,
    val getNetworkNews: GetNetworkNews
) : ViewModel() {

    val categories = listOf(
        "business", "entertainment", "general", "health", "science", "sports", "technology"
    )
    fun launchCatching(
        snackbar: Boolean = true,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(
        CoroutineExceptionHandler { _, throwable ->
            if (snackbar) {
                SnackbarManager.showMessage(throwable.toSnackbarMessage())
            }
        },
        block = block
    )

    // TODO: initialise network data here one time (this logic should be performed during app
    //  startup/onboarding/or splash ... basically load the data before the user gets to home page
    //  for best ux experience)... Also, this saves bandwidth (you only make network requests when you need to)
    init {
       launchCatching {
           getNews().collect { domainArticles ->
               if (domainArticles.isEmpty()) {
                    // since we have only seven categories, prefill the db with initial data, and load more data only when user requests, e.g during refresh
                   categories.forEach {
                       getNetworkNews(it)
                   }
               }
           }
       }
    }
}