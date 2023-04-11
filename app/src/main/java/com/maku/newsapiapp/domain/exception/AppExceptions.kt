package com.maku.newsapiapp.domain.exception

import java.io.IOException

/**
 * Custom exceptions classes
 * **/
class NetworkUnavailableException(message: String = "No network available :(") : IOException(message)

class NetworkException(message: String): Exception(message)

