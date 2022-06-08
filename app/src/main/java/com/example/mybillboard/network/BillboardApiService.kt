package com.example.mybillboard.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://billboardapi2.free.beeceptor.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface BillboardApiService {
    @GET("hot-100")
    suspend fun getHot100(): List<NetworkChartItem>

    @GET("song/{id}")
    suspend fun getSong(@Path("id") id: String): NetworkSong

    @GET("search/{term}")
    suspend fun search(@Path("term") term: String): List<NetworkSearchItem>
}

object BillboardApi {
    val retrofitService: BillboardApiService by lazy {
        retrofit.create(BillboardApiService::class.java)
    }
}