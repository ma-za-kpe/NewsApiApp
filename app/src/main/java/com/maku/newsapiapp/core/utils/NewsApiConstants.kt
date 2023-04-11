package com.maku.newsapiapp.core.utils

// https://newsapi.org/v2/top-headlines?category=sports&apiKey=17e807cdexxx45aabcbc2920a27f6837
object ApiConstants {
    const val BASE_ENDPOINT = "https://newsapi.org/v2/"
    const val HEADLINES_ENDPOINT = "top-headlines"
}

object ApiParameters {
    const val CATEGORY = "category"
    const val API_KEY = "apiKey"
}
