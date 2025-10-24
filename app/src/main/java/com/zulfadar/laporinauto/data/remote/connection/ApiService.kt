package com.zulfadar.laporinauto.data.remote.connection

import com.zulfadar.laporinauto.data.remote.response.AllReportResponseItem
import com.zulfadar.laporinauto.data.remote.response.ListVehicleResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {
    @GET("read_all_laporan")
    suspend fun getAllLaporan(
        @Query("userId") userId: String = "krlnjzhjxtzbezxgvmvq"
    ): List<AllReportResponseItem>

    @GET("list_vehicle")
    suspend fun getListVehicle(): ListVehicleResponse

    @Multipart
    @POST("add_laporan")
    suspend fun addLaporan(
        @Part("vehicleId") vehicleId: RequestBody,
        @Part("note") note: RequestBody,
        @Part("userId") userId: RequestBody,
        @Part photo: MultipartBody.Part? = null
    )
}