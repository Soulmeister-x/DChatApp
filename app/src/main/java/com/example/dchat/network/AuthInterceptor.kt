package com.example.dchat.network

import okhttp3.Interceptor
import okhttp3.Response

/*
Source: https://medium.com/@harmittaa/retrofit-2-6-0-with-koin-and-coroutines-4ff45a4792fc
The purpose of the interceptor is to intercept all outgoing requests.
In this example the interceptor is used to insert the API key as a query
parameter, as that’s how the OpenWeatherMap API wants it.
 */
class AuthInterceptor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url = req.url().newBuilder().build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}
