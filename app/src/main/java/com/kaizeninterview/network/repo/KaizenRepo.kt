package com.kaizeninterview.network.repo

import com.kaizeninterview.domain.cmn.Sport
import com.kaizeninterview.network.KaizenApi
import javax.inject.Inject

class KaizenRepo @Inject constructor(val api: KaizenApi) {

    suspend fun fetchAllSports(): List<Sport> {
        return api.fetchAllSports()
    }
}