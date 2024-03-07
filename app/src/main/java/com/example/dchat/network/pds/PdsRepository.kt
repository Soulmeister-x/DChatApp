package com.example.dchat.network.pds

import com.example.dchat.network.PdsApi
import org.koin.dsl.module

val pdsModule = module {
    factory { PdsRepository(get()) }
}

class PdsRepository(private val pdsApi: PdsApi) {
    suspend fun getHealth() = pdsApi.getPdsHealth()
}
