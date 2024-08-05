package com.kostuciy.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BinService {
    @GET("{bin}")
    suspend fun getInfo(
        @Path("bin") bin: Long,
    ): Response<CardResponse>
}
