package com.example.dchat.di

import com.example.dchat.network.AuthInterceptor
import com.example.dchat.network.PdsApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Source: https://medium.com/@harmittaa/retrofit-2-6-0-with-koin-and-coroutines-4ff45a4792fc

val networkModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { providePdsApi(get()) }
    single { provideRetrofit(get()) }
}

// TODO: move baseUrl to sharedPreferences or BuildConfig
const val BASE_URL = "https://vivoliva.de/xrpc/"

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient =
    OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()

fun providePdsApi(retrofit: Retrofit): PdsApi =
    retrofit.create(PdsApi::class.java)
