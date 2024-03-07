package com.example.dchat.network

import com.example.dchat.network.pds.Health
import retrofit2.http.GET

interface PdsApi {
    // test connection
    @GET("_health")
    suspend fun getPdsHealth(): Health
}
