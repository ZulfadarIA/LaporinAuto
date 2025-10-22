package com.zulfadar.laporinauto.data.remote.connection

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("read_all_laporan")
    suspend fun getAllLaporan(
        @Query("userId") userId: String
    )
}