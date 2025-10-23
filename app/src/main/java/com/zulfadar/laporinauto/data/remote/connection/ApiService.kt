package com.zulfadar.laporinauto.data.remote.connection

import com.zulfadar.laporinauto.data.remote.response.AllReportResponseItem
import com.zulfadar.laporinauto.data.remote.response.ListVehicleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("read_all_laporan")
    suspend fun getAllLaporan(
        @Query("userId") userId: String = "krlnjzhjxtzbezxgvmvq"
    ): List<AllReportResponseItem>

    @GET("list_vehicle")
    suspend fun getListVehicle(): ListVehicleResponse
}