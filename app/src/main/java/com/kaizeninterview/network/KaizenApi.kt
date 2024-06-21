package com.kaizeninterview.network

import com.kaizeninterview.domain.cmn.Sport
import retrofit2.http.GET

interface KaizenApi {

    @GET("sports")
    suspend fun fetchAllSports(): List<Sport>
}