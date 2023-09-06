package com.example.throw_fornt.data.model.request

import com.example.throw_fornt.data.model.response.StoreInfoBySearchNameResponse
import com.example.throw_fornt.data.model.response.StoreModel
import com.example.throw_fornt.data.model.response.StoresInfoByLocationResponse
import com.example.throw_fornt.data.model.response.testBody
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface StoreRequest {

    // 상점 등록 request
    @POST("store")
    fun registerRequest(
        @Body register: Register,
    ): Call<String>

    // 내 가게 조회 request
    @POST("/api/...")
    fun storeRequest(
        @Header("Content-Type") key: String,
    ): Call<StoreModel>

    // 사업자등록번호 조회 request
    @POST("store/crn")
    fun bnoRequest(
        @Body crn: HashMap<String, String>,
    ): Call<StoreModel>

    // 가게 수정 request
    @PUT("store")
    fun mondifyRequest(
        @Field("extStoreId") uuid: String,
        @Field("storePhone") storePhone: String,
        @Field("latitudes") latitudes: Double,
        @Field("longitude") longitude: Double,
        @Field("crn") bno: String,
        @Field("zipCode") zipCode: String,
        @Field("fullAddress") fullAddress: String,
        @Field("trashType") type: String,
    ): Call<StoreModel>

    // 가게 삭제 request
    @DELETE("store")
    fun deleteRequest(
        @Field("extStoreId") uuid: String,
        @Field("storeName") storeName: String,
        @Field("storePhone") storePhone: String,
        @Field("latitudes") latitudes: Double,
        @Field("longitude") longitude: Double,
        @Field("crn") bno: String,
        @Field("zipCode") zipCode: String,
        @Field("fullAddress") fullAddress: String,
        @Field("trashType") type: String,
    ): Call<StoreModel>

    @GET("fapi")
    fun getRequest(
        @Query("key") key: String,
        @Query("gb") gb: String,
        @Query("q") q: String,
        @Query("type") type: String,
    ): Call<testBody>

    @POST("store/search")
    fun getStoresInfoByLocation(
        @Body storesInfoByLocationRequest: StoresInfoByLocationRequest,
    ): Call<List<StoresInfoByLocationResponse>>

    @POST("store/name")
    fun getStoresInfoBySearchName(
        @Body storeInfoBySearchNameForMapRequest: StoreSearchNameForMapRequest,
    ): Call<List<StoreInfoBySearchNameResponse>>
}

data class Register(
    @SerializedName("storePhone") val storePhone: String, // 가게 전화번호
    @SerializedName("crn") val crn: String, // 사업자등록번호
    @SerializedName("latitude") val latitude: Double, // 위도 (-90~90)
    @SerializedName("longitude") val longitude: Double, // 경도 (-180~180)
    @SerializedName("zipCode") val zipCode: String, // 우편번호
    @SerializedName("fullAddress") val fullAddress: String, // 지번주소
    @SerializedName("trashType") val trashType: String, // 일쓰->병->플라스틱->종이->캔
)