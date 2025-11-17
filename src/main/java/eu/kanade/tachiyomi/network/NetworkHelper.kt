package eu.kanade.tachiyomi.network

import okhttp3.OkHttpClient

val OkHttpClient.cloudflareClient: OkHttpClient
    get() = this
