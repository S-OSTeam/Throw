package com.example.throw_fornt.data.model.request

import com.example.throw_fornt.data.model.response.StoreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StoreRequest {
    /*
    상점 등록 request
     */
    @GET("/api/...")
    fun registerRequest(
        @Query("address_id") addrressId : String,
        @Query("location_id") locationId : String,
        @Query("name") name : String,
        @Query("company_registration_number") bno : String,
        @Query("type") type : String,
        @Query("second_password") secondPassword : String,
    ) : Call<StoreResponse>

    /*
    상점 데이터 request
     */
    @GET("/api/...")
    fun storeRequest(
    ) : Call<StoreResponse>

    @GET("")
    fun bnoRequest(
        @Query("bno") company_registration_number : String
    ) : Call<StoreResponse>
}
